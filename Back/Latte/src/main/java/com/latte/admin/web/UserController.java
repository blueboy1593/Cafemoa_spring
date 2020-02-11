package com.latte.admin.web;

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
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private final KakaoAPI kakaoAPI;

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
    public Map signIn(@RequestBody UserJwtRequestDto userJwtRequestDto, HttpServletResponse response, HttpServletRequest request) {
        Map<String,String> map=new HashMap<>();
        String secPass=encrypt(userJwtRequestDto.getUpass());
        UserJwtResponsetDto userJwtResponsetDto = userService.signIn(userJwtRequestDto.getUid(),secPass);
        if (userJwtResponsetDto != null && request.getCookies() == null) {
            String token = jwtService.create(userJwtResponsetDto);
            cm.CookieMake(request,response,token);
            map.put("token",token);
            return map;
        }
        map.put("token",request.getCookies()[0].getValue());
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

    static String Static_access_Token=null;
    @GetMapping(value = "/kakaologin")
    public Map login(@RequestParam("code") String code,HttpServletRequest request,HttpServletResponse response) {
        Map<String,String> map=new HashMap<>();
        String access_Token = kakaoAPI.getAccessToken(code);
        Static_access_Token=access_Token;
        HashMap<String, String> userInfo = kakaoAPI.getUserInfo(access_Token);
        if(userInfo.get("email")==null){
            map.put("email",null); //동의를 구하는 Component
            System.out.println(map);
            return map;
        }
//        System.out.println("login Controller : " + userInfo);
        //여기서는 값얻어옴
        String curEmail=userInfo.get("email");
        if(!userService.checkEmail(curEmail)){ //이메일 가능함
            System.out.println("가능합니다!!!!!!!!!!!회원가입시키세요!!!!!!!!!!!!!!");
            System.out.println(userInfo);
            return userInfo;
        }else{ //이미 존재하는 이메일이면
            User user=userService.findByEmail(curEmail);
            if(user.getUpass().equals(userInfo.get("Id"))){
                //이미 카카오톡으로 회원가입을 한 사람.
                String secPass=encrypt(userInfo.get("Id"));
                UserJwtResponsetDto userJwtResponsetDto = userService.signIn(user.getUid(),secPass);
                if (userJwtResponsetDto != null && request.getCookies() == null) {
                    String token = jwtService.create(userJwtResponsetDto);
                    cm.CookieMake(request,response,token);
                    map.put("token",token);
                    System.out.println(map);
                    return map;
                }
                map.put("token",request.getCookies()[0].getValue());
                System.out.println(map);
                return map;
            }else{
                map.put("email","false");
                System.out.println(map);
                return map;
            }
        }
        //프론트는 가입 요청을 보낸다음에 (KakaoLogin이나 , NaverLogin이나)
        //그런다음 돌아오는 return 값을 get("email")로 확인하여 null이면 이메일 동의가 필요하다는 Component로
        //get("email")이 false이면 이메일이 이미 존재한다는 Component로
        //get("token")이 null이 아니면 이미 이 이메일로 가입했기에 우리의 JWT를 주고 로그인 다음 화면으로 넘김
        //token을 받으려면 get("token")으로 받으시면 됨.
        //셋다 아니라면 회원가입 페이지에 우리가 보내준 정보들을 readOnly로 뿌리고 회원가입을 시키면 된다.
    }

    @GetMapping(value="/kakaologout")
    public String logout() {
        System.out.println("카카오톡 로그아웃!!!!!!!!!!!");
        kakaoAPI.kakaoLogout(Static_access_Token);
        Static_access_Token=null;
        return "카카오톡 로그아웃~아웃~ 아웃~ 아~ 아웃이에요 어차피 안써요";
    }
}
