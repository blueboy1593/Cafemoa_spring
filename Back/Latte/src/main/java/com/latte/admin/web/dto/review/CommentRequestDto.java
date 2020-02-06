package com.latte.admin.web.dto.review;

import com.latte.admin.domain.review.Comment;
import com.latte.admin.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String comment;

    @Builder
    public CommentRequestDto(String comment) {
        this.comment=comment;
    }

    public Comment toEntity(Review review) {
        return Comment.builder()
                .comment(comment)
                .review(review)
                .build();
    }
}
