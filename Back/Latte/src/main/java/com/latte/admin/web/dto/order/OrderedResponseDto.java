package com.latte.admin.web.dto.order;

import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class OrderedResponseDto {
    private User orderuser;

    @Builder
    public OrderedResponseDto(Ordered entity){
        this.orderuser=entity.getOrderuser();
    }
}