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
    private String mpic;

    @Column
    private int isMain;

    @Column
    private int mtype;

    @Column
    private int mprice;

    // fk -> 1:N = cafe:menu
    @ManyToOne(optional = false)
    @JsonBackReference
    private Cafe cafemenu;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordermenu")
    @JsonBackReference
    private List<OrderDetail> orderDetail;


    @Builder
    public Menu(Cafe cafemenu,String mname,String mpic,int isMain,int mtype,int mprice) {
        this.cafemenu=cafemenu;
        this.mname=mname;
        this.mpic=mpic;
        this.isMain=isMain;
        this.mtype=mtype;
        this.mprice=mprice;
    }


    public void update(String mname,String mpic,int isMain,int mtype,int mprice) {
        this.mname=mname;
        this.mpic=mpic;
        this.isMain=isMain;
        this.mtype=mtype;
        this.mprice=mprice;
    }

    public void toggleMainMenu(){
        this.isMain=1-this.isMain;
    }
}