package com.latte.admin.web;

import com.fasterxml.jackson.databind.JsonNode;
//import com.latte.admin.service.KakaoService;
import com.latte.admin.service.UserService;
import com.latte.admin.service.jwt.CookieManage;
import com.latte.admin.service.jwt.JwtService;
import com.latte.admin.service.jwt.UnauthorizedException;
import com.latte.admin.web.dto.user.*;
import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/latte/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
//    private final KakaoService kakaoService;

    private CookieManage cm=new CookieManage();

    // 회원 가입
    @PostMapping("/signup")
    public void signUp(@RequestBody UserSaveRequestDto userSaveRequestDto) {
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
        System.out.println("jwt가 뭡니까? :"+jwt);
        if (!jwtService.isUsable(jwt))  return;
        UserJwtResponsetDto user=jwtService.getUser(jwt);
        System.out.println("현재 유저 : "+user.getUid());
        userService.update(user.getUid(), userUpdateRequestDto);
        // 기존 토큰 죽이기
        System.out.println("기존 토큰을 삭제합니다.");

        cm.CookieDelete(request,response);
        System.out.println("지금 쿠키수 : "+request.getCookies().length);
        //토큰 재발행
        System.out.println("토큰을 재발행합니다.");
        String token = jwtService.create(new UserJwtResponsetDto(userService.findByuid(user.getUid())));
        cm.CookieMake(request,response,token);
    }

    // 삭제
    @DeleteMapping("/delete")
    public void delete(@RequestBody UserDeleteRequestDto userDeleteRequestDto, HttpServletResponse response, HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);

        if(user.getUid().equals(userDeleteRequestDto.getUid())){
            userService.delete(user.getUid());
            Cookie cookie = request.getCookies()[0];
            cookie.setValue(null);
            cookie.setPath("/"); // <- 여기 잘 모르겠음
            cookie.setMaxAge(0);//나이 0살 - 죽은거야
            response.addCookie(cookie);
        }else throw new UnauthorizedException(); // 예외
    }

    // 로그인
    @ApiOperation("로그인하면서 토큰을 발행")
    @PostMapping("/signin")
    public String signIn(@RequestBody UserJwtRequestDto userJwtRequestDto, HttpServletResponse response, HttpServletRequest request) {
        String secPass=encrypt(userJwtRequestDto.getUpass());
        UserJwtResponsetDto userJwtResponsetDto = userService.signIn(userJwtRequestDto.getUid(),secPass);
        if (userJwtResponsetDto != null && request.getCookies() == null) {
            String token = jwtService.create(userJwtResponsetDto);
            cm.CookieMake(request,response,token);
            return token;
        }
        return request.getCookies()[0].getValue();
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
            cm.CookieDelete(request,response);
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


//    @ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
//    @PostMapping(value = "/signin/{provider}")
//    public SingleResult<String> signinByProvider(
//            @ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
//            @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {
//
//        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
//        User user = userJpaRepo.findByUidAndProvider(String.valueOf(profile.getId()), provider).orElseThrow(CUserNotFoundException::new);
//        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
//    }


    @RequestMapping(value = "/kakaologin", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
    public Map kakaoLogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        System.out.println("카카오톡 code :"+code);


        JsonNode node = UserService.getAccessToken(code); // accessToken에 사용자의 로그인한 모든 정보가 들어있음
        System.out.println("AccessToken : "+ node.get("access_token"));

        JsonNode accessToken = node.get("access_token"); // 사용자의 정보
        JsonNode userInfo = UserService.getKakaoUserInfo(accessToken);
        String kemail = null;
        String kname = null;
        String kimage = null; // 유저정보 카카오에서 가져오기 Get properties

        JsonNode properties = userInfo.path("properties");
        JsonNode kakao_account = userInfo.path("kakao_account");
        kemail = kakao_account.path("email").asText();
        kname = properties.path("nickname").asText();
        kimage = properties.path("profile_image").asText();
        Map<String,String> map=new HashMap<>();
        map.put("uemail",kemail);
        map.put("uname",kname);
        map.put("upic",kimage);
        return map;
    }// end kakaoLogin()






}
