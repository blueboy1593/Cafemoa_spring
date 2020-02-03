package com.latte.admin.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtService {

    private static final String SALT = "LatteSecret";
    private static String KEY = "";

    // 토큰 발행(JWT 만들기)
    // JWT의 헤더, 클레임, 암호 등의 필요한 정보를 넣고 직렬화
    // session처럼 활용하기 위해서는 claim에 데이터를 넣으면 된다.
    public <T> String create(String key, T data) { // user
        KEY = key;
        String jwt = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .claim(key, data)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact(); // <- 요기가 직렬화
        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (log.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                log.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }
        return key;
    }

    // claim으로 변환
    public boolean isUsable(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt); // 변환중

            return true; // 변환 완료 -> 유효한 토큰, true 반환
        } catch (Exception e) {
            throw new UnauthorizedException(); // 예외
        }
    }

    // JWT에 넣어 놓은 데이터를 가져온다.
    public Map<String, Object> get(String jwt) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SALT.getBytes("UTF-8"))
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
        System.out.println(claims.getBody().get(KEY));
//        @SuppressWarnings("unchecked")
//        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get("member");//Response
        Map<String, Object> hm = new HashMap<>();
        hm.put("UserJwtResponseDto", claims.getBody());
        return hm;
    }

}
