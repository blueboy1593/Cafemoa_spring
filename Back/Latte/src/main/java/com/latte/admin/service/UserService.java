package com.latte.admin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latte.admin.domain.user.User;
import com.latte.admin.domain.user.UserRepository;
import com.latte.admin.web.dto.user.UserJwtResponsetDto;
import com.latte.admin.web.dto.user.UserSaveRequestDto;
import com.latte.admin.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;

    public List<User> selectAll(){
        return userRepository.findAll();
    }


    //이메일로 엔티티 가져오기
    @Transactional
    public User findByEmail(String email){
        return userRepository.findByEmail(email).get(0);
    }



    // 회원 가입
    @Transactional
    public boolean signUp(UserSaveRequestDto userSaveRequestDto) {
        System.out.println(userSaveRequestDto);
        // insert 전에 테이블을 검색해서 중복된 이메일이 있는지 확인한다.

        //우리 회원가입 로직은 이메일로만 중복검사를 실행합니다.!!!
        if (checkEmail(userSaveRequestDto.getUemail())) //이미 이메일이 있으면
            return false;

        // 회원가입완료하면 이메일로 ghld 보내준다.
        // 2번일 경우 테이블에 승인 상태 추가해야 됨
        MailService mailService = new MailService();
        mailService.setJavaMailSender(javaMailSender);
        mailService.sendSimpleMessage(userSaveRequestDto.getUemail(), "[라떼는 말이야] 회원가입 완료", "ㅎㅇㅎㅇ");

        userRepository.save(userSaveRequestDto.toEntity()).getUid();
        return true;
    }


    // 회원가입시 이메일 중복 확인
    @Transactional
    public boolean checkEmail(String uemail) {
        List<User> user = userRepository.findByEmail(uemail);
        if(user.size()>0) return true; //있으면 1
        else return false; //없으면 0
    }

    // 아이디 중복 확인 (있으면 true, 없으면 false)
    @Transactional
    public boolean checkId(String uid) {
        List<User> user = userRepository.checkByUid(uid);
        if(user.size()>0) return true;
        else return false;
    }

    // 아이디 찾기
    @Transactional
    public String findId(String uname, String uemail) {
        List<User> user = userRepository.findByNameEmail(uname, uemail);
        if(user.size()==1){
            return user.get(0).getUid();
        }else{
            return "해당하는 정보가 없습니다.";
        }

    }

    // 비밀번호 찾기
    @Transactional
    public String findPass(String uid, String uemail) {
        if(!checkId(uid)) return "존재하지 않는 ID 입니다.";

        User user=userRepository.findByuid(uid);

        if (user.getUemail().equals(uemail)) {
            // 비밀번호 생성
            String new_pass = generatePass(10);
            // 이메일로 비밀번호 쏴주고!!
            MailService mailService = new MailService();
            mailService.setJavaMailSender(javaMailSender);
            mailService.sendSimpleMessage(uemail, "[라떼는말이야] 비밀번호 재설정", "비밀번호: " + new_pass);
            // 테이블에 있는 회원 비밀번호 그걸로 수정!!!!! -> 암호화
            userRepository.updatePass(uid, SHA256Util.getEncrypt(new_pass));
        } else {
            new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }

        return user.getUemail();
    }

    // 비밀번호 생성 메소드
    public String generatePass(int length) {
        StringBuffer sb = new StringBuffer();
        char[] charSet = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
                , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'
                , 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                , 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
                , 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        for (int i = 0; i < length; i++) {
            int index = (int) (charSet.length * Math.random());
            sb.append(charSet[index]);
        }

        return sb.toString();
    }

    //바뀐 유저 데이터에 대해서 토큰을 재발행 할 때
    //JwtUserRequest를 만들기 위한 작업으로 필요함.
    //DB까지 가지않고 서비스를 이용하여 끌고옴
    @Transactional
    public User findByuid(String uid){
        return userRepository.findByuid(uid);
    }


    // 회원 정보 수정
    @Transactional
    public String update(String uid, UserUpdateRequestDto userUpdateRequestDto) {
        User user=userRepository.findByuid(uid);
        if(user==null)
            new IllegalArgumentException("해당 사용자가 없습니다.");

        user.update(userUpdateRequestDto.getUpass(), userUpdateRequestDto.getUphone(), userUpdateRequestDto.getUnickname(),userUpdateRequestDto.getUpic());
        return uid;
    }

    // 탈퇴(삭제)
    @Transactional
    public void delete(String uid) {
        User user=userRepository.findByuid(uid);
        if(user==null)
            new IllegalArgumentException("해당 사용자가 없습니다.");

        userRepository.delete(user);
    }

    // 로그인
    @Transactional
    public UserJwtResponsetDto signIn(String uid, String upass) {
        User user=userRepository.findByuid(uid);
        if(user==null)
            new IllegalArgumentException("해당 사용자가 없습니다.");


        if (user.getUpass().equals(upass)) {
            return new UserJwtResponsetDto(user);
        } else {
            new IllegalArgumentException("아이디/비밀번호가 일치하지 않습니다.");
            return null;
        }
    }

//    private final static String K_CLIENT_ID = "f19ae1c386503f9082e85e5431870f4f"; //이런식으로 REDIRECT_URI를 써넣는다.// //
//    private final static String K_REDIRECT_URI = "http://70.12.246.69:8080/latte/user/kakaologin";
//    public static String getAuthorizationUrl(HttpSession session) {
//        String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + K_CLIENT_ID + "&redirect_uri=" + K_REDIRECT_URI + "&response_type=code";
//        return kakaoUrl;
//    }
//
//    public static JsonNode getAccessToken(String authorize_code) {
//        final String RequestUrl = "https://kauth.kakao.com/oauth/token";
//        final List<NameValuePair> postParams = new ArrayList<>();
//        postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
//        postParams.add(new BasicNameValuePair("client_id",K_CLIENT_ID));// REST API KEY
//        postParams.add(new BasicNameValuePair("redirect_uri", K_REDIRECT_URI)); // 리다이렉트 URI
//        postParams.add(new BasicNameValuePair("code", authorize_code)); // 로그인 과정중 얻은 code 값
//        final HttpClient client = HttpClientBuilder.create().build();
//        final HttpPost post = new HttpPost(RequestUrl);
//        JsonNode returnNode = null;
//        try {
//            post.setEntity(new UrlEncodedFormEntity(postParams));
//            final HttpResponse response = client.execute(post); // JSON 형태 반환값 처리
//            ObjectMapper mapper = new ObjectMapper();
//            returnNode = mapper.readTree(response.getEntity().getContent());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally { // clear resources
//        }
//
//        System.out.println("KaKaoLogin returnNode : "+returnNode);
//
//        return returnNode;
//
//    }
//
//    public static JsonNode getKakaoUserInfo(JsonNode accessToken) {
//        final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
//        final HttpClient client = HttpClientBuilder.create().build();
//        final HttpPost post = new HttpPost(RequestUrl); // add header
//        post.addHeader("Authorization", "Bearer " + accessToken);
//        JsonNode returnNode = null;
//        try {
//            final HttpResponse response = client.execute(post);
//            // JSON 형태 반환값 처리
//            ObjectMapper mapper = new ObjectMapper();
//            returnNode = mapper.readTree(response.getEntity().getContent());
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally { // clear resources
//        }
//
//        System.out.println("Token을 이용한 사용자 정보를 얻는 함수 내 출력입니다.");
//        System.out.println("returnNode : "+returnNode);
//
//        return returnNode;
//    }

}
