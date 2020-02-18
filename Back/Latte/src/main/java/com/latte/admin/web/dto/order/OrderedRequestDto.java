package com.latte.admin.web.dto.order;

import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderedRequestDto {

    private String ocontent;
    private Long oprice;

    @Builder
    public OrderedRequestDto(String ocontent,Long oprice) {
        this.ocontent=ocontent;
        this.oprice=oprice;
    }

    public Ordered toEntity(User user) {
        return Ordered.builder()
                .orderuser(user)
                .ocontent(ocontent)
                .oprice(oprice)
                .build();
    }
}
