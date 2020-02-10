package com.latte.admin.domain.config.auth.dto;

import com.latte.admin.domain.user.Role;
import com.latte.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    //of() : OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야만 합니다.
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofNaver("id", attributes);  // 아마 여기에 카카오 들어가야 할듯
    }

    // Naver에서 생일을 가지고왔지만 사용하지 않음
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    //User 엔티티를 생성합니다.
    //OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때입니다.
    //가입할 때의 기본 권한을 GUEST로 주기 위해서 role 빌더값에는 Role.GUEST를 사용합니다.
    //OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성합니다.
    public User toEntity() {
        return User.builder()
                .uname(name)
                .uemail(email)
                .role(Role.GUEST) //기본적으로 User의 Role에는 Guest가 들어가는 것.
                .build();
    }
}
