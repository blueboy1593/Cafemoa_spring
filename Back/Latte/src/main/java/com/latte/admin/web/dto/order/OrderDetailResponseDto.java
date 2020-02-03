package com.latte.admin.web.dto.order;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.order.OrderDetail;
import com.latte.admin.domain.order.Ordered;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {
    private Ordered ordered;
    private Menu ordermenu;

    @Builder
    public OrderDetailResponseDto(OrderDetail entity) {
        this.ordered=entity.getOrdered();
        this.ordermenu=entity.getOrdermenu();
    }
}
