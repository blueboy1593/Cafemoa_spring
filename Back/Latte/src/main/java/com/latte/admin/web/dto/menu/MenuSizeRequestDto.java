package com.latte.admin.web.dto.menu;


import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.menu.MenuSize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuSizeRequestDto {
    private String msname;
    private int msprice;

    public MenuSizeRequestDto(String msname,int msprice){
        this.msname=msname;
        this.msprice=msprice;
    }

    public MenuSize toEntity(Menu menu){
        return MenuSize.builder()
                .msname(msname)
                .msprice(msprice)
                .menu(menu)
                .build();
    }
}
