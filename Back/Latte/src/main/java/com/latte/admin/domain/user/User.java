package com.latte.admin.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class User {

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

    // 사진 처음에 입력하지 말고, 로그인 이후에 mypage에서 넣을 수 있도록 하기!!
    /* 프사!!!!!!!!!!!!!!!! */

    // fk -> 1:N = user:order -> if role=1(손님)
    //user-order의 관계는 user가 연관관계의 대상.
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "orderuser")
    @JsonManagedReference
    private Ordered ordered;

    public User(Long uuid) {
        this.uuid=uuid;
    }

    @Builder
    public User(String uid, String upass, String uname, String uphone, String uemail, String unickname, Role role, Cafe cafe) {
        this.uid = uid;
        this.upass = upass;
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.unickname = unickname;
        this.role = role;
    }

    // 수정
    public void update(String upass, String uphone, String unickname) {
        this.upass = upass;
        this.uphone = uphone;
        this.unickname = unickname;
    }

}
