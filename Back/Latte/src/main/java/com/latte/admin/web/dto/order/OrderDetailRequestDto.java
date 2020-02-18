package com.latte.admin.web.dto.order;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.order.OrderDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailRequestDto {

    private Long mmid; //메뉴가져오고
    private int pay; //가격 가져와(프론트에서 옵션,사이즈,수량 계산해서 보낸 가격)

    @Builder
    public OrderDetailRequestDto(Long mmid,int pay) {
        this.mmid=mmid;
        this.pay=pay;
    }

    public OrderDetail toEntity(Menu ordermenu, Ordered ordered) {
        return OrderDetail.builder()
                .ordered(ordered)
                .ordermenu(ordermenu)
                .build();
    }
}
