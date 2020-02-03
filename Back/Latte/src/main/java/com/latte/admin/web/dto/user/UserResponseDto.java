package com.latte.admin.web.dto.user;

import com.latte.admin.domain.user.Role;
import com.latte.admin.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long uuid;
    private String uid;
    private String upass;
    private String uname;
    private String uphone;
    private String uemail;
    private String unickname;
    private Role role;
    private String upic;

    public UserResponseDto(User entity) {
        this.uuid = entity.getUuid();
        this.uid = entity.getUid();
        this.upass = entity.getUpass();
        this.uname = entity.getUname();
        this.uphone = entity.getUphone();
        this.uemail = entity.getUemail();
        this.unickname = entity.getUnickname();
        this.role = entity.getRole();
        this.upic=entity.getUpic();
    }
}
