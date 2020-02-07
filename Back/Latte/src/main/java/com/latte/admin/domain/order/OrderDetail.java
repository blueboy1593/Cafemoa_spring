package com.latte.admin.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.latte.admin.domain.BaseTimeEntity;
import com.latte.admin.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odid;

    // fk -> 1:1 = orderDetail:menu
    //orderdetail-menu 의 관계는 orderdetail이 연관관계의 주인
    @ManyToOne
    @JsonBackReference
    private Menu ordermenu;

    // fk -> 1:N = order:orderDetail
    //order-orderdetail의 연관관계는 orderdetail이 연관관계의 주인임
    @ManyToOne(optional = false)
    @JsonBackReference
    private Ordered ordered;

    @Builder
    public OrderDetail(Menu ordermenu,Ordered ordered) {
        this.ordermenu=ordermenu;
        this.ordered = ordered;
    }
}
