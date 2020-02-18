package com.latte.admin.web.dto.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@NoArgsConstructor
@Getter
public class MenuUpdateRequestDto {
    private String mname;
    private String mpic;
    private int isMain;
    private int mtype;
    private int mprice;

    @Builder
    public MenuUpdateRequestDto(String mname,String mpic,int isMain,int mtype,int mprice) {
        this.mname = mname;
        this.mpic = mpic;
        this.isMain=isMain;
        this.mtype=mtype;
        this.mprice=mprice;
    }
}