package com.latte.admin.web.dto.order;

import com.latte.admin.domain.order.Ordered;

public class OrderListResponseDto {
    private Long ooid;
    private int ostatus;  // 주문상태: -1=취소, 0=대기, 1=사장님확인, 2=완료
    private String ocontent;
    private Long oprice;

    public OrderListResponseDto(Ordered entity) {
        this.ooid=entity.getOoid();
        this.ostatus=entity.getOstatus();
        this.ocontent=entity.getOcontent();
        this.oprice=entity.getOprice();
    }
}
