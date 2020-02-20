package com.latte.admin.web;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.latte.admin.domain.user.User;
import com.latte.admin.service.KakaoAPI;
import com.latte.admin.service.UserService;
import com.latte.admin.service.jwt.CookieManage;
import com.latte.admin.service.jwt.JwtService;
import com.latte.admin.service.jwt.UnauthorizedException;
import com.latte.admin.web.dto.user.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PropertySource("classpath:config.properties")
@CrossOrigin("*")
@RestController
@RequestMapping("/latte/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @Value("${client_secret}")
    private String client_secret;


    @Autowired
    private final KakaoAPI kakaoAPI;

    private CookieManage cm = new CookieManage();

    //모든 유저의 정보를 드린다.
    @ApiOperation("모든 유저의 정보를 출력합니다.")
    @GetMapping("/all")
    public List<User> selectAll() {
        return userService.selectAll();
    }


    // 회원 가입
    @PostMapping(value = "/signup", consumes = "application/json")
    public void signUp(@RequestBody UserSaveRequestDto userSaveRequestDto) {
//        @RequestParam("upic") MultipartFile multipartFile
//        System.out.println(multipartFile.getName());
//        System.out.println(multipartFile.getOriginalFilename());
//        System.out.println(multipartFile.getContentType());
//        System.out.println(multipartFile.getSize());
        String secPass = encrypt(userSaveRequestDto.getUpass());
        userSaveRequestDto.setUpass(secPass);
        userService.signUp(userSaveRequestDto);
    }

    // 아이디 중복 확인(회원가입시)
    @PostMapping("/checkid/{uid}")
    public boolean checkId(@PathVariable String uid) {
        return userService.checkId(uid);
    }

    // 아이디 찾기
    @PostMapping("/findid")
    public Map findId(@RequestBody UserFindIdRequestDto userFindIdRequestDto) {
        Map<String, String> map = new HashMap<>();
        map.put("id", userService.findId(userFindIdRequestDto.getUname(), userFindIdRequestDto.getUemail()));
        return map;
    }

    // 비밀번호 찾기
    @PostMapping("/findpass/{uid}/{uemail}")
    public Map findPass(@PathVariable String uid, @PathVariable String uemail) {
        Map<String, String> map = new HashMap<>();
        map.put("email", userService.findPass(uid, uemail));
        return map;
    }

    // 비밀번호 확인 -> login로직에서 있으면 안하고, 없으면 한다!!!!!!!!!!


    // 회원 정보 수정 -> mypage에서 pass, nickname, phone 변경 가능
    @PutMapping("/update")
    public void update(HttpServletResponse response, HttpServletRequest request, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        String jwt = request.getHeader("Authorization");
        if (!jwtService.isUsable(jwt)) return;
        UserJwtResponsetDto user = jwtService.getUser(jwt);
        userService.update(user.getUid(), userUpdateRequestDto);
        // 기존 토큰 죽이기

        cm.CookieDelete(request, response);
        //토큰 재발행
        System.out.println("토큰을 재발행합니다.");
        String token = jwtService.create(new UserJwtResponsetDto(userService.findByuid(user.getUid())));
        cm.CookieMake(request, response, token);
    }

    // 삭제
    @DeleteMapping("/delete")
    public void delete(@RequestBody UserDeleteRequestDto userDeleteRequestDto, HttpServletResponse response, HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user = jwtService.getUser(jwt);

        if (user.getUid().equals(userDeleteRequestDto.getUid())) {
            userService.delete(user.getUid());
            Cookie cookie = request.getCookies()[0];
            cookie.setValue(null);
            cookie.setPath("/"); // <- 여기 잘 모르겠음
            cookie.setMaxAge(0);//나이 0살 - 죽은거야
            response.addCookie(cookie);
        } else throw new UnauthorizedException(); // 예외
    }

    // 로그인
    @ApiOperation("로그인하면서 토큰을 발행")
    @PostMapping("/signin")
    public Map signIn(@RequestBody UserJwtRequestDto userJwtRequestDto, HttpServletResponse response, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        String secPass = encrypt(userJwtRequestDto.getUpass());
        UserJwtResponsetDto userJwtResponsetDto = userService.signIn(userJwtRequestDto.getUid(), secPass);
        if (userJwtResponsetDto != null && request.getCookies() == null) {
            String token = jwtService.create(userJwtResponsetDto);
            cm.CookieMake(request, response, token);
            map.put("token", token);
            return map;
        }
        map.put("token", request.getCookies()[0].getValue());
        return map;
    }

//
//    @ApiOperation("토큰을 디코딩하는 함수 => 디코딩시 유저정보(id,email,role 등) 가지고 있음")
//    @GetMapping("/getUser/{jwt}")
//    public Object getData(@PathVariable String jwt) {
//        return jwtService.get(jwt);
//    }
//
//    @ApiOperation("토큰을 검증하는 함수 => 이 토큰이 유효한 것인지 확인")
//    @GetMapping("/isTrueToken/{jwt}")
//    public Map isTrueToken(@PathVariable String jwt, HttpServletRequest request) {
//
//        Map<String, Boolean> hm = new HashMap<>();
//        hm.put("result", jwtService.isUsable(jwt));
//        hm.put("result2", jwtService.isUsable(request.getCookies()[0].getValue()));
//        return hm;
//    }

    @GetMapping("/logout")
    public void logOut(HttpServletResponse response, HttpServletRequest request) {
        cm.CookieDelete(request, response);
    }


    public static String encrypt(String rawpass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(rawpass.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    static String Static_access_Token = null;

    @ApiOperation("카카오 로그인")
    @PostMapping("/Kakaologin")
    public Map login(@RequestBody RequestKakaoCodeDto requestKakaoCodeDto ,HttpServletRequest request,HttpServletResponse response) {
        System.out.println("날라온 코드 : "+requestKakaoCodeDto.getCode());
        String access_Token = kakaoAPI.getAccessToken(requestKakaoCodeDto.getCode());
        HashMap<String, String> userInfo = kakaoAPI.getUserInfo(access_Token);
        Map<String,String> map=new HashMap<>();
        if(userInfo.get("email")==null){
            userInfo.put("status","0"); //동의를 구하는 Component
            userInfo.put("message","이메일 동의가 필요합니다.");
            return userInfo;
        }
//        System.out.println("login Controller : " + userInfo);
        //여기서는 값얻어옴
        String curEmail=userInfo.get("email");
        if(!userService.checkEmail(curEmail)){ //이메일 가능함
            System.out.println(userInfo);
            userInfo.put("status","3");
            return userInfo;
        }else{ //이미 존재하는 이메일이면
            User user=userService.findByEmail(curEmail);
            String secPass=encrypt(userInfo.get("Id"));
            UserJwtResponsetDto userJwtResponsetDto = userService.signIn(user.getUid(),secPass);
            if (userJwtResponsetDto != null && request.getCookies() == null) {
                String token = jwtService.create(userJwtResponsetDto);
                cm.CookieMake(request,response,token);
                map.put("token",token);//이미 회원가입이 됨.
                map.put("status","1");
                System.out.println(map);
                return map;
            }
            map.put("token",request.getCookies()[0].getValue());
            map.put("status","1");
            System.out.println(map);
            return map;
        }
    }

    @ApiOperation("네이버 로그인")
    @PostMapping("/Naverlogin")
    public Map userSigninNaver(HttpServletRequest request,HttpServletResponse response, @RequestBody NaverResponseDto naverResponseDto) throws Exception {
        System.out.println("지금 들어온 코드 :" + naverResponseDto.getNcode());

        Map<String, String> map = new HashMap<>();
        HttpStatus status = null;
        // client_id, client_sercret, code, state를 가지고 네이버에 token 요청
        String cline_id = "FA7itQNNdqjv2zVuS9_R";
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
        apiURL += "&client_id=" + cline_id;
        apiURL += "&client_secret=" + client_secret;
        apiURL += "&code=" + naverResponseDto.getNcode();
        apiURL += "&state=" + naverResponseDto.getNstate();
        String access_token = "";
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if (responseCode == 200) { // 정상호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer res = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }
        br.close();
        if (responseCode == 200) { // 성공적으로 토큰을 가져오면
            int id;
            String nickname, email, tmp;
            JsonParser parser = new JsonParser();
            JsonElement accessElement = parser.parse(res.toString());
            access_token = accessElement.getAsJsonObject().get("access_token").getAsString();
            tmp = userService.getUserInfo(access_token);
            JsonElement userInfoElement = parser.parse(tmp);
            id = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsInt();
            nickname = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("nickname").getAsString();
            email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();

            if (email == null) {
                map.put("status", "0"); //동의를 구하는 Component
                map.put("message", "이메일 동의가 필요합니다.");
                return map;
            }

            boolean b_find = userService.checkEmail(email);

            if (!b_find) { // 없으면 추가폼 받으러 ㄱㄱ
                map.put("status", "3"); //가입시키라는 폼.
                map.put("email", email);
                map.put("nickname", nickname);
                return map;
            } else { //이미 있으면 토큰발급
                User user = userService.findByEmail(email);
                String secPass = encrypt(id + ""); //아이디당 하나 고유값인거셈.
                UserJwtResponsetDto userJwtResponsetDto = userService.signIn(user.getUid(), secPass);
                if (userJwtResponsetDto != null && request.getCookies() == null) {
                    String token = jwtService.create(userJwtResponsetDto);
                    cm.CookieMake(request, response, token);
                    map.put("token", token);//이미 회원가입이 됨.
                    map.put("status", "1");
                    System.out.println(map);
                    return map;
                }
                map.put("token", request.getCookies()[0].getValue());
                map.put("status", "1");
                System.out.println(map);
                return map;
            }
        } else {
            map.put("status", "-1");
            map.put("message", "에러가 발생하였습니다.");
            return map;
        }
    }

    private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpstatus, boolean status) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", status);
        resultMap.put("data", data);
        return new ResponseEntity<Map<String, Object>>(resultMap, httpstatus);
    }


//    // 사진 입력
//    @PostMapping("/upload")
//    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
//                                                          @RequestParam("email") String email) throws Exception {
//        Map<String, Object> resultMap = new HashMap<>();
//        User user = userService.findByEmail(email);
//        if (file == null) {
//            return response(resultMap, HttpStatus.ACCEPTED, false);//이부분 모르겠는데 영연이형한테 물어봐야함 실패했을때 뭐리턴함?
//        }
//        File root = new File("./uploads");
//        if (root.exists() && !user.getUpic().equals("default.png")) { //파일존재여부
//            File[] files = root.listFiles();
//            for (File f : files) {
//                if (f.getName().equals(user.getUpic())) {
//                    f.delete();
//                }
//            }
//        }
//
//        String fileName = fileUploadDownloadService.storeFile(file);
//        user.setUpic(fileName);
////        userService.uploadFile(user);
//        return response(resultMap, HttpStatus.ACCEPTED, true);
//    }
}
