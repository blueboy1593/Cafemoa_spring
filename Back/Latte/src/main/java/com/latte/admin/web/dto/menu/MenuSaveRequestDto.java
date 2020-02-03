package com.latte.admin.web.dto.menu;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuSaveRequestDto {

    private String mname;
    private String mprice;
    private String mpic;

    @Builder
    public MenuSaveRequestDto(String mname,String mprice,String mpic) {
        this.mname = mname;
        this.mprice = mprice;
        this.mpic = mpic;
    }

    public Menu toEntity(Long ccid) {
        return Menu.builder()
                .cafemenu(new Cafe(ccid))
                .mname(mname)
                .mprice(mprice)
                .mpic(mpic)
                .build();
    }
}