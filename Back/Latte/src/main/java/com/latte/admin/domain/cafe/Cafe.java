package com.latte.admin.domain.cafe;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.latte.admin.domain.BaseTimeEntity;
import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cafe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ccid;

    @Column
    private String uid;

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

    @Column(nullable = false)
    private int coperation;  // 1:운영중, 0:끝

    private String latitude;

    private String longitude;

    // fk -> 1:N = cafe:menu
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "cafemenu")
    @JsonManagedReference
    private List<Menu> menus=new ArrayList<>();

    // 1:N = cafe:review
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "cafereview")
    @JsonManagedReference
    private List<Review> reviews=new ArrayList<>();

//    // fk -> 1:N = cafe:order
//    @OneToMany(cascade=CascadeType.ALL, mappedBy = "ordercafe")
//    @JsonManagedReference
//    private List<Ordered> ordered=new ArrayList<>();


    public Cafe(Long ccid){
        this.ccid=ccid;
    }

    @Builder
    public Cafe(String uid,String cname, String cloc, String cphone, String cpic, String copen, String cdesc,
                String cclose, int cstatus,int coperation,String latitude,String longitude) {
        this.uid=uid;
        this.cname = cname;
        this.cloc = cloc;
        this.cphone = cphone;
        this.cpic = cpic;
        this.copen = copen;
        this.cclose = cclose;
        this.cdesc=cdesc;
        this.cstatus = cstatus;
        this.coperation=coperation;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public void CafeUpdate(String cname,String cloc,String cphone,String cpic,String copen,
                           String cclose,String cdesc,String latitude,String longitude) {
        this.cname=cname;
        this.cloc=cloc;
        this.cphone=cphone;
        this.cpic=cpic;
        this.copen=copen;
        this.cclose=cclose;
        this.cdesc=cdesc;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public void setStatus(int cstatus){
        this.cstatus=cstatus;
    }

    public void setCoperation(int coperation) {this.coperation=coperation;}
}