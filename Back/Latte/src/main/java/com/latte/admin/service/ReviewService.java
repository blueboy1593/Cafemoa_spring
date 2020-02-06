package com.latte.admin.service;

import com.latte.admin.domain.review.CommentRepository;
import com.latte.admin.domain.review.Review;
import com.latte.admin.domain.review.ReviewRepository;
import com.latte.admin.web.dto.review.CommentRequestDto;
import com.latte.admin.web.dto.review.ReviewResponseDto;
import jdk.jfr.Threshold;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public final ReviewRepository reviewRepository;
    public final CommentRepository commentRepository;

    // 저장
    @Transactional
    public void save(Review review) {
        reviewRepository.save(review);
    }

    // 전체보기
    @Transactional
    public List<ReviewResponseDto> findByCcid(Long ccid) {
        List<ReviewResponseDto> list=new ArrayList<>();

        List<Review> temp=reviewRepository.findByCcid(ccid);

        for(Review rv:temp) {
            list.add(new ReviewResponseDto(rv));
        }

        return list;
    }

    // comment저장
    @Transactional
    public Long saveComment(CommentRequestDto commentRequestDto, Long rvid) {
        Review review=reviewRepository.findById(rvid).get();
        return commentRepository.save(commentRequestDto.toEntity(review)).getCoid();
    }
}
