package com.latte.admin.web;

import com.latte.admin.domain.user.User;
import com.latte.admin.service.UserService;
import com.latte.admin.service.jwt.JwtService;
import com.latte.admin.web.dto.user.UserJwtRequestDto;
import com.latte.admin.web.dto.user.UserJwtResponseDto;
import com.latte.admin.web.dto.user.UserSaveRequestDto;
import com.latte.admin.web.dto.user.UserUpdateRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/latte/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;


    // 회원 가입
    @PostMapping("/signup")
    public void signUp(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        userService.signUp(userSaveRequestDto);
    }

    // 아이디 중복 확인
    @PostMapping("/checkid/{uid}")
    public void checkId(@PathVariable String uid) {
        userService.checkId(uid);
    }

    // 아이디 찾기
    @PostMapping("/findid/{uname}/{uemail}")
    public Map findId(@PathVariable String uname, @PathVariable String uemail) {
        Map<String, String> map = new HashMap<>();
        map.put("id", userService.findId(uname, uemail));
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
    public String update(HttpServletResponse response, HttpServletRequest request, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        String jwt = request.getCookies()[0].getValue();
        if (!jwtService.isUsable(jwt))
            return null;
        Map<String, Object> map = jwtService.get(jwt);
        UserJwtResponseDto userJwtResponseDto = (UserJwtResponseDto) map.get("UserJwtResponseDto");
        userService.update(userJwtResponseDto.getUid(), userUpdateRequestDto);

        // 토큰 재발행
        String token = jwtService.create("member", userJwtResponseDto);
        Cookie cookie = new Cookie("userCookie", token);
        cookie.setPath("/"); // <- 여기 잘 모르겠음
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        return cookie.getValue(); // 토큰 반환
    }

    // 삭제
    @DeleteMapping("/delete")
    public void delete(HttpServletResponse response, HttpServletRequest request) {
        // x
        String jwt = request.getCookies()[0].getValue();
        if (!jwtService.isUsable(jwt))
            return;
        Map<String, Object> map = jwtService.get(jwt);
        UserJwtResponseDto user = (UserJwtResponseDto) map.get("UserJwtResponseDto");
        userService.delete(user.getUid());
    }

    // 로그인
    @ApiOperation("로그인하면서 토큰을 발행")
    @PostMapping("/signin")
    public String signIn(@RequestBody UserJwtRequestDto userJwtRequestDto, HttpServletResponse response, HttpServletRequest request) {
        UserJwtResponseDto userJwtResponseDto = userService.signIn(userJwtRequestDto.getUid(), userJwtRequestDto.getUpass());
        if (userJwtResponseDto != null && request.getCookies() == null) {
            String token = jwtService.create("member", userJwtResponseDto);
            Cookie cookie = new Cookie("userCookie", token);
            cookie.setPath("/"); // <- 여기 잘 모르겠음
            cookie.setMaxAge(Integer.MAX_VALUE);
            // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            response.addCookie(cookie);
            System.out.println("발행된 토큰 : " + cookie.getValue());
            return cookie.getValue();
        }
        return request.getCookies()[0].getValue();
    }


    @ApiOperation("토큰을 디코딩하는 함수 => 디코딩시 유저정보(id,email,role 등) 가지고 있음")
    @GetMapping("/getUser/{jwt}")
    public Map getData(@PathVariable String jwt) {
        return jwtService.get(jwt);
    }

    @ApiOperation("토큰을 검증하는 함수 => 이 토큰이 유효한 것인지 확인")
    @GetMapping("/isTrueToken/{jwt}")
    public Map isTrueToken(@PathVariable String jwt, HttpServletRequest request) {

        Map<String, Boolean> hm = new HashMap<>();
        hm.put("result", jwtService.isUsable(jwt));
        hm.put("result2", jwtService.isUsable(request.getCookies()[0].getValue()));
        return hm;
    }

    @GetMapping("/logout")
    public void logOut(HttpServletResponse response, HttpServletRequest request) {
//       Cookie cookie = new Cookie("userCookie", "");
        Cookie cookie = request.getCookies()[0];
        cookie.setValue(null);
        cookie.setPath("/"); // <- 여기 잘 모르겠음
        cookie.setMaxAge(0);//나이 0살 - 죽은거야
        response.addCookie(cookie);
        System.out.println("".equals(null));
        System.out.println("로그아웃 되었습니다.");
        System.out.println("현재 쿠키수 :" + request.getCookies().length);
        System.out.println("현재 쿠키 :" + request.getCookies()[0].getValue());
    }
}
