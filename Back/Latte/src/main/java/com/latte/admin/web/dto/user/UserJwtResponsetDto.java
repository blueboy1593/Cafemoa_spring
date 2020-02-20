package com.latte.admin.web.dto.user;

import com.latte.admin.domain.user.Role;
import com.latte.admin.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserJwtResponsetDto {
    private Long uuid;
    private String uid;
    private String uname;
    private String uphone;
    private String uemail;
    private String unickname;
    private Role role;
    private String upic;


    public UserJwtResponsetDto(User entity) {
        this.uuid=entity.getUuid();
        this.uid = entity.getUid();
        this.uname = entity.getUname();
        this.uphone = entity.getUphone();
        this.uemail = entity.getUemail();
        this.unickname = entity.getUnickname();
        this.role = entity.getRole();
        this.upic=entity.getUpic();
    }
}
