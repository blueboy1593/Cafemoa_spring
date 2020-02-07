package com.latte.admin.domain.review;


import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.latte.admin.domain.BaseTimeEntity;
import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rvid;

    @Column(columnDefinition = "TEXT", length = 1000)
    private String rvcontent;

    @Column
    private String rvpic;

    @Column
    private String rvuid;

    @Column
    private int rvlike;

    @OneToMany(mappedBy = "reviewlike")
    @JsonBackReference
    private List<ReviewLike> reviewLikes;

    @ManyToOne(optional = false)
    @JsonBackReference
    private Cafe cafereview;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList;


    @Builder
    public Review(String rvcontent,String rvpic,int rvlike,String rvuid,Cafe cafereview) {
        this.rvcontent=rvcontent;
        this.rvpic=rvpic;
        this.rvuid=rvuid;
        this.rvlike=rvlike;
        this.cafereview=cafereview;
    }

    public void update(String rvcontent,String rvpic) {
        this.rvpic=rvpic;
        this.rvcontent=rvcontent;
    }

    public void liketoggle(boolean likeStatus) {
        if(likeStatus) {this.rvlike -= 1;}
        else {this.rvlike += 1;}
    }

}
