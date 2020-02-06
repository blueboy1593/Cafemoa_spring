package com.latte.admin.web;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.menu.Menu;
import com.latte.admin.service.CafeService;
import com.latte.admin.service.ReviewService;
import com.latte.admin.service.jwt.JwtService;
import com.latte.admin.service.jwt.UnauthorizedException;
import com.latte.admin.web.dto.review.CommentRequestDto;
import com.latte.admin.web.dto.review.ReviewResponseDto;
import com.latte.admin.web.dto.review.ReviewSaveRequestDto;
import com.latte.admin.web.dto.user.UserJwtResponsetDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/latte/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtService jwtService;
    private final CafeService cafeService;

    // 리뷰 저장
    // ccid, uid-> token
    @ApiOperation("카페마다 손님들의 리뷰를 저장하는 기능")
    @PostMapping("/{ccid}")
    public Map save(@PathVariable Long ccid, HttpServletRequest httpServletRequest, @RequestBody ReviewSaveRequestDto reviewSaveRequestDto) {
        String jwt = httpServletRequest.getCookies()[0].getValue();
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외
        UserJwtResponsetDto user=jwtService.getUser(jwt);


        Cafe cafe=cafeService.findByCcId(ccid);
        reviewService.save(reviewSaveRequestDto.toEntity(cafe,user.getUid()));

        Map<String,String> map=new HashMap<>();
        map.put("result","리뷰가 저장되었습니다~");
        return map;
    }

    // 리뷰 전체 보기
    @ApiOperation("리뷰 전체 보기")
    @GetMapping("/{ccid}")
    public List<ReviewResponseDto> selectAll(@PathVariable Long ccid) {
        return reviewService.findByCcid(ccid);
    }

    // 리뷰 수정


    // 사장님 댓글 작성   -> 사장님인지 확인(토큰)
    @ApiOperation("사장님 댓글 작성(comment)")
    @PostMapping("/comment/{rvid}")
    public Long saveComment(@PathVariable Long rvid, @RequestBody CommentRequestDto commentRequestDto) {
        return reviewService.saveComment(commentRequestDto, rvid);
    }

}
