package com.latte.admin.web.dto.menu;

import com.latte.admin.domain.menu.Menu;
import lombok.Getter;

@Getter
public class MenuResponseDto {
    private Long mmid;
    private Long ccid;  // 주문해서 장바구니갔을때, 어느 카페에서 뭘시켰는지 확인할때 필요!
    private String mname;
    private String mpic;
    private int mtype;
    private Long mprice;

    public MenuResponseDto(Menu entity) {
        this.mmid=entity.getMmid();
        this.ccid=entity.getCafemenu().getCcid();
        this.mname=entity.getMname();
        this.mpic=entity.getMpic();
        this.mtype=entity.getMtype();
        this.mprice=entity.getMprice();
    }

}