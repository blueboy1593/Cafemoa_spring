package com.latte.admin.web.dto.menu;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuUpdateRequestDto {
    private String mname;
    private String mprice;
    private String mpic;

    @Builder
    public MenuUpdateRequestDto(String mname,String mprice,String mpic) {
        this.mname = mname;
        this.mprice = mprice;
        this.mpic = mpic;
    }
}
