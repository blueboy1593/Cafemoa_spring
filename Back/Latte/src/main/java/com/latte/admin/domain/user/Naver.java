package com.latte.admin.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Naver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    @Column
    private String nnick;

    @Column
    private String nemail;

    @Builder
    public Naver(String nnick,String nemail) {
        this.nnick=nnick;
        this.nemail=nemail;
    }

}
