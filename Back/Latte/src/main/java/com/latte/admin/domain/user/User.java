package com.latte.admin.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.latte.admin.domain.BaseTimeEntity;
import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.order.Ordered;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 오토 인크리먼트
    private Long uuid; // 1, 2, 3, ...

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String upass;

    @Column(nullable = false)
    private String uname;

    @Column(nullable = false)
    private String uphone;

    @Column(nullable = false)
    private String uemail;

    @Column(nullable = false)
    private String unickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String upic;

    // fk -> 1:N = user:order -> if role=1(손님)
    //user-order의 관계는 user가 연관관계의 대상.
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "orderuser")
    @JsonManagedReference
    private Ordered ordered;

    public User(Long uuid) {
        this.uuid=uuid;
    }

    @Builder
    public User(String uid, String upass, String uname, String uphone, String uemail, String unickname, Role role, String upic) {
        this.uid = uid;
        this.upass = upass;
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.unickname = unickname;
        this.role = role;
        this.upic=upic;
    }

    // 수정
    public void update(String upass, String uphone, String unickname) {
        this.upass = upass;
        this.uphone = uphone;
        this.unickname = unickname;
        this.upic=upic;
    }

}
