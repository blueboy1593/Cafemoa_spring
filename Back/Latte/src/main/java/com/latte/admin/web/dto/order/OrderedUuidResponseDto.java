package com.latte.admin.web.dto.order;

import com.latte.admin.domain.order.Ordered;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderedUuidResponseDto {

    private int ostatus;  // 주문상태: -1=취소, 0=대기, 1=사장님확인, 2=완료
    private String ocontent;
    private Long oprice;
    private Long ccid;

    @Builder
    public OrderedUuidResponseDto(Ordered entity){
        this.ostatus=entity.getOstatus();
        this.ocontent=entity.getOcontent();
        this.oprice=entity.getOprice();
        this.ccid=entity.getCcid();
    }
}