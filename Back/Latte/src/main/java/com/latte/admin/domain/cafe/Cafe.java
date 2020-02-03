package com.latte.admin.domain.cafe;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.latte.admin.domain.BaseTimeEntity;
import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
public class Cafe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ccid;

    @Column(nullable = false)
    private String cname;

    @Column(nullable = false)
    private String cloc;

    @Column(nullable = true)
    private String cphone;

    @Column(nullable = true)
    private String cpic;

    @Column(nullable = false)
    private String copen;

    @Column(nullable = false)
    private String cclose;

    @Column(nullable = false)
    private String cdesc;

    @Column(nullable = false)
    private int cstatus;  // -1:승인X, 0:대기, 1:승인

    @Column
    private int coperation;  // 1:운영중, 0:끝

    // fk -> 1:N = cafe:menu
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "cafemenu")
    @JsonManagedReference
    private Collection<Menu> menus=new ArrayList<>();

    public Cafe(Long ccid){
        this.ccid=ccid;
    }

    @Builder
    public Cafe(User user,String cname, String cloc, String cphone, String cpic, String copen, String cdesc, String cclose, int cstatus,int coperation) {
        this.cname = cname;
        this.cloc = cloc;
        this.cphone = cphone;
        this.cpic = cpic;
        this.copen = copen;
        this.cclose = cclose;
        this.cdesc=cdesc;
        this.cstatus = cstatus;
        this.coperation=coperation;
    }

    public void CafeUpdate(String cphone,String cpic,String copen,String cclose,String cdesc,int cstatus,int coperation) {
        this.cphone = cphone;
        this.cpic = cpic;
        this.copen = copen;
        this.cclose = cclose;
        this.cdesc=cdesc;
        this.cstatus = cstatus;
        this.coperation=coperation;
    }


    public void statusSet(int cstatus){
        this.cstatus=cstatus;
    }


}
