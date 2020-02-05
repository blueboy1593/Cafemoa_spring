package com.latte.admin.service;

import com.latte.admin.domain.user.User;
import com.latte.admin.domain.user.UserRepository;
import com.latte.admin.web.dto.user.UserJwtResponsetDto;
import com.latte.admin.web.dto.user.UserSaveRequestDto;
import com.latte.admin.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;

    // 회원 가입
    @Transactional
    public boolean signUp(UserSaveRequestDto userSaveRequestDto) {
        // insert 전에 테이블을 검색해서 중복된 이메일이 있는지 확인한다.
        if (!checkEmail(userSaveRequestDto.getUemail()))
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
        if(userRepository.findByEmail(uemail) == null) {
            new IllegalArgumentException("사용할 수 없는 이메일입니다.");
            return false;
        }

        return  true;
    }

    // 아이디 중복 확인
    @Transactional
    public void checkId(String uid) {
        User user = userRepository.checkByUid(uid).orElseThrow(()
                -> new IllegalArgumentException("사용 가능한 아이디입니다."));

        new IllegalArgumentException("사용 불가능한 아이디입니다.");
    }

    // 아이디 찾기
    @Transactional
    public String findId(String uname, String uemail) {
        User user = userRepository.findByNameEmail(uname, uemail).orElseThrow(()
                -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return user.getUid();
    }

    // 비밀번호 찾기
    @Transactional
    public String findPass(String uuid, String uemail) {
        User user = userRepository.checkByUid(uuid).orElseThrow(()
                -> new IllegalArgumentException("존재하지 않는 ID입니다."));

        if (user.getUemail().equals(uemail)) {
            // 비밀번호 생성
            String new_pass = generatePass(10);
            // 이메일로 비밀번호 쏴주고!!
            MailService mailService = new MailService();
            mailService.setJavaMailSender(javaMailSender);
            mailService.sendSimpleMessage(uemail, "[라떼는말이야] 비밀번호 재설정", "비밀번호: " + new_pass);
            // 테이블에 있는 회원 비밀번호 그걸로 수정!!!!! -> 암호화
            userRepository.updatePass(uuid, SHA256Util.getEncrypt(new_pass));
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
        User user = userRepository.checkByUid(uid).orElseThrow(()
                -> new IllegalArgumentException("해당 사용자가 없습니다."));

        user.update(userUpdateRequestDto.getUpass(), userUpdateRequestDto.getUphone(), userUpdateRequestDto.getUnickname(),userUpdateRequestDto.getUpic());

        return uid;
    }

    // 탈퇴(삭제)
    @Transactional
    public void delete(String uid) {
        User user = userRepository.checkByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        userRepository.delete(user);
    }

    // 로그인
    @Transactional
    public UserJwtResponsetDto signIn(String uid, String upass) {
        User user = userRepository.checkByUid(uid).orElseThrow(() -> new IllegalArgumentException("아이디/비밀번호가 일치하지 않습니다."));
        if (user.getUpass().equals(upass)) {
            return new UserJwtResponsetDto(user);
        } else {
            new IllegalArgumentException("아이디/비밀번호가 일치하지 않습니다.");
            return null;
        }
    }

}
