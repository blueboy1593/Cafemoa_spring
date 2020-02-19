package com.latte.admin.web.dto.menu;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MenuSaveRequestDto {

    private String mname;
    private String mpic;
    private int mtype;
    private Long mprice;

    @Builder
    public MenuSaveRequestDto(String mname,String mpic,int mtype,Long mprice) {
        this.mname = mname;
        this.mpic = mpic;
        this.mtype=mtype;
        this.mprice=mprice;
    }

    public Menu toEntity(Cafe cafe) {
        return Menu.builder()
                .cafemenu(cafe) //조인용
                .mname(mname)
                .mpic(mpic)
                .isMain(0)
                .mtype(mtype)
                .mprice(mprice)
                .build();
    }

}