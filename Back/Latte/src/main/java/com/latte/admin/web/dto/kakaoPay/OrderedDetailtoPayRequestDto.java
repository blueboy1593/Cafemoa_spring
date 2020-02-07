package com.latte.admin.web.dto.kakaoPay;


import com.latte.admin.domain.order.OrderDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderedDetailtoPayRequestDto {
    Long ccid;
    Long ooid;
    String orderuserid;
    List<OrderDetailtoPay> orderDetails;


}
