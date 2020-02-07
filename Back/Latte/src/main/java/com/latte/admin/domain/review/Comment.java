package com.latte.admin.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.latte.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coid;

    @Column
    private String couid;

    @ManyToOne(optional = false)
    @JsonBackReference
    private Review review;

    @Column(columnDefinition = "TEXT", length = 500)
    private String comment;

    @Builder
    public Comment(String couid,Review review, String comment) {
        this.couid=couid;
        this.review=review;
        this.comment=comment;
    }

    public void update(String comment) {
        this.comment=comment;
    }

}
