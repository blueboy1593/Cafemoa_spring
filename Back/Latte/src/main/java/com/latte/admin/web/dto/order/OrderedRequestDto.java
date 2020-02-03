package com.latte.admin.web.dto.order;

import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderedRequestDto {
    public Ordered toEntity(User user) {
        return Ordered.builder()
                .orderuser(user)
                .build();
    }
}
