package com.latte.admin.web.dto.user;

import com.latte.admin.service.SHA256Util;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String upass;
    private String uphone;
    private String unickname;
    private String upic;

    @Builder
    public UserUpdateRequestDto(String upass, String uphone, String unickname, String upic) {
        this.upass = upass;
        this.uphone = uphone;
        this.unickname = unickname;
        this.upic=upic;
    }
}
