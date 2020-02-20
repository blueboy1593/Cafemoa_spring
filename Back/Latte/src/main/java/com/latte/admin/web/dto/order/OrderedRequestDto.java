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
    private int oprice;
    private int ostatus;
    private Long uuid;
    private Long ccid;

    @Builder
    public OrderedRequestDto(String ocontent,int oprice,int ostatus,Long uuid,Long ccid) {
        this.ocontent=ocontent;
        this.oprice=oprice;
        this.ostatus=ostatus;
        this.uuid=uuid;
        this.ccid=ccid;
    }

    public Ordered toEntity(User user) {
        return Ordered.builder()
                .orderuser(user)
                .ocontent(ocontent)
                .oprice(oprice)
                .ccid(ccid)
                .build();
    }
}
