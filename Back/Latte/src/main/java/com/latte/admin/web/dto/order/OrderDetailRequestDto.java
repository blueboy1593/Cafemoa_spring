package com.latte.admin.web.dto.order;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.options.Option;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.order.OrderDetail;
import com.latte.admin.web.dto.menu.MenuOptionRequestDto;
import com.latte.admin.web.dto.menu.MenuSizeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailRequestDto {

    private Long mmid; //메뉴가져오고
    private MenuSizeRequestDto menuSizeRequestDto; //사이즈 가져오고
    private MenuOptionRequestDto menuOptionRequestDto; //옵션 가져오고
    private int pay; //가격 가져와(프론트에서 옵션,사이즈,수량 계산해서 보낸 가격)

    public OrderDetailRequestDto(Long mmid,MenuSizeRequestDto menuSizeRequestDto,MenuOptionRequestDto menuOptionRequestDto,int pay) {
        this.mmid=mmid;
        this.menuSizeRequestDto=menuSizeRequestDto;
        this.menuOptionRequestDto=menuOptionRequestDto;
        this.pay=pay;
    }

    public OrderDetail toEntity(Menu ordermenu, Ordered ordered) {
        return OrderDetail.builder()
                .ordered(ordered)
                .ordermenu(ordermenu)
                .build();
    }
}
