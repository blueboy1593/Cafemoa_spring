package com.latte.admin.domain.config.auth.dto;

import com.latte.admin.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user){
        this.name=user.getUname();
        this.email=user.getUemail();
    }
}
