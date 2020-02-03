package com.latte.admin.web.dto.user;

import com.latte.admin.domain.user.Role;
import com.latte.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJwtRequestDto {
    private String uid;
    private String upass;

    @Builder
    public UserJwtRequestDto(String uid,String upass){
        this.uid=uid;
        this.upass=upass;
    }
}
