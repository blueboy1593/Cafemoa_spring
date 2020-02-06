package com.latte.admin.domain.menu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.latte.admin.domain.BaseTimeEntity;
import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.order.OrderDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Menu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mmid;

    @Column(nullable = false)
    private String mname;

    @Column(nullable = false)
    private String mprice;

    @Column(nullable = false)
    private String mpic;

    // fk -> 1:N = cafe:menu
    @ManyToOne(optional = false)
    @JsonBackReference
    private Cafe cafemenu;

    // fk -> 1:1 = orderDetail:menu
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordermenu")
    @JsonBackReference
    private List<OrderDetail> orderDetail;

    @Builder
    public Menu(Cafe cafemenu,String mname,String mprice,String mpic) {
        this.cafemenu=cafemenu;
        this.mname=mname;
        this.mprice=mprice;
        this.mpic=mpic;
    }

    public void update(String mname,String mprice,String mpic) {
        this.mname=mname;
        this.mprice=mprice;
        this.mpic=mpic;
    }
}