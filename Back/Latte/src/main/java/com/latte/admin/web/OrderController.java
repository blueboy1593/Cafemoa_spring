package com.latte.admin.web;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.user.User;
import com.latte.admin.service.*;
import com.latte.admin.service.jwt.JwtService;
import com.latte.admin.service.jwt.UnauthorizedException;
import com.latte.admin.web.dto.order.OrderDetailRequestDto;
import com.latte.admin.web.dto.order.OrderDetailResponseDto;
import com.latte.admin.web.dto.order.OrderStatusRequestDto;
import com.latte.admin.web.dto.user.UserJwtResponsetDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderedService orderedService;
    private final OrderDetailService orderDetailService;
    private final MenuService menuService;
    private final JwtService jwtService;
    private final UserService userService;
    private final KakaoPayService kakaoPayService;

    // 주문메뉴 리스트 보여주기
    @ApiOperation("[손님 장바구니]:주문 메뉴 리스트를 보여주기")
    @GetMapping("/latte/orderdetail/{ooid}")
    public List<OrderDetailResponseDto> selectAll(@PathVariable Long ooid) {
        return orderDetailService.findAllDesc(ooid);
    }


    // jwt 필요
    // 주문 상황 변경 -> 주문상태: -1=취소, 0=대기, 1=사장님확인, 2=완료  // 취소했다고 db에서 삭제하지는 않음
    @ApiOperation("[사장/손님 주문상태 확인페이지]: -1=취소(1이 되기전 0일때는 취소가능), 0=대기, 1=사장님확인, 2=완료")
    @PostMapping("/latte/order/setStatus/{ooid}")
    public int cafeStatusSet(@PathVariable Long ooid, @RequestBody OrderStatusRequestDto orderStatusRequestDto) {
        Long ccid= orderStatusRequestDto.getOoid();
        int ostatus= orderStatusRequestDto.getOstatus();
        orderedService.setStatus(ostatus);
        return orderedService.findById(ooid).getOstatus();
    }


    // ordered table을 보여주는 이유
    // 1. 손님: 마이페이지에서 history보여줌
    // 2. 사장: 판매내역 확인 가능
    // if 손님이 한번에 여러개 카페에서 주문한다면, 그 카페마다 주문이 들어왔다고 알려야함.
    @ApiOperation("카페에서 메뉴를 주문하는 기능입니다.")
    @PostMapping("/latte/ordersave")
    public Map save(@RequestBody List<OrderDetailRequestDto> orderDetailRequestDtos,HttpServletRequest httpServletRequest){
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외

        UserJwtResponsetDto user=jwtService.getUser(jwt);

        Map<String,String> map=new HashMap<>();
        //현재 orderDetailRequestDtos에는 메뉴, 옵션 등의 정보가 담겨 있다.
        //현재 초기 코드는 메뉴만 있다고 가정.
        User orderuser=userService.findByuid(user.getUid());
        System.out.println("현재 주문하는 유저는 : "+orderuser.getUname()+"님 입니다.");
        Ordered ordered=orderedService.findById(orderedService.save(orderuser));
        //Ordered 테이블에 먼저 만들어지고나서 orderDetail이 존재할 수 있다.

        for(OrderDetailRequestDto odrequset:orderDetailRequestDtos){
            Long curmmid=odrequset.getMmid();
            System.out.println("지금 mmid : "+curmmid);
            System.out.println(curmmid.getClass());
            Menu ordermenu=menuService.findById(curmmid);
            System.out.println("지금 ordermenu : "+ordermenu.getMname());
            orderDetailService.save(odrequset.toEntity(ordermenu,ordered));
        }
       kakaoPayService.kakaoPayReady(ordered,TotalPay);

        map.put("result","주문이 완료되었습니다 ^^");
        return map;
    }



    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }


    public String kakaoPay(@RequestBody Total) {


    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

    }


}
