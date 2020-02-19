package com.latte.admin.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.latte.admin.domain.BaseTimeEntity;
import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Ordered extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ooid;

    @Column(nullable = true)
    private int ostatus;  // 주문상태: -1=취소, 0=대기, 1=사장님확인, 2=완료

    @Column
    private String ocontent;

    @Column(nullable = true)
    private Long oprice;

    @Column
    private Long ccid;

//    // fk -> 1:N = cafe:order
//    @ManyToOne(optional = false)
//    @JsonBackReference
//    private Cafe ordercafe;

    // fk -> 1:N = user:order -> if role=1(손님)
    //user-ordered의 관계는 ordered가 연관관계의 주인
    @ManyToOne(optional = false)
    @JsonBackReference
    private User orderuser;

    // Pay관련 정보는 추후에 추가 예정

    @Builder
    public Ordered(User orderuser,int ostatus,String ocontent,Long oprice,Long ccid){
        this.orderuser=orderuser;
        this.ostatus=ostatus;
        this.ocontent=ocontent;
        this.oprice=oprice;
        this.ccid=ccid;
    }

}
