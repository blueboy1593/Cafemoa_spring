package com.latte.admin.web.dto.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindIdRequestDto {
    String uname;
    String uemail;

    public FindIdRequestDto(String uname,String uemail){
        this.uname=uname;
        this.uname=uemail;
    }
}
