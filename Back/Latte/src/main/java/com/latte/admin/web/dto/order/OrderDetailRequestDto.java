package com.latte.admin.web.dto.order;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.order.OrderDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailRequestDto {

    private Long mmid;

    public OrderDetailRequestDto(Long mmid){
        this.mmid=mmid;
    }

    public OrderDetail toEntity(Menu ordermenu, Ordered ordered) {
        return OrderDetail.builder()
                .ordered(ordered)
                .ordermenu(ordermenu)
                .build();
    }
}
