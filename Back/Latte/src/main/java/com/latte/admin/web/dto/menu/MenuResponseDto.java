package com.latte.admin.web.dto.menu;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.menu.MenuSize;
import com.latte.admin.domain.options.Option;
import lombok.Getter;

import java.util.List;

@Getter
public class MenuResponseDto {
    private Long mmid;
    private Long ccid;  // 주문해서 장바구니갔을때, 어느 카페에서 뭘시켰는지 확인할때 필요!
    private String mname;
    private String mpic;
    private List<MenuSize> menuSize;
    private List<Option> menuOption;

    public MenuResponseDto(Menu entity) {
        this.mmid=entity.getMmid();
        this.ccid=entity.getCafemenu().getCcid();
        this.mname=entity.getMname();
        this.mpic=entity.getMpic();
        this.menuSize=entity.getMenuSizes();
        this.menuOption=entity.getOptionList();
    }

}