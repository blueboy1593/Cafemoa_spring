package com.latte.admin.web;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.user.User;
import com.latte.admin.service.*;
import com.latte.admin.service.jwt.JwtService;
import com.latte.admin.service.jwt.UnauthorizedException;
import com.latte.admin.web.dto.order.OrderListResponseDto;
import com.latte.admin.web.dto.order.OrderStatusRequestDto;
import com.latte.admin.web.dto.order.OrderedRequestDto;
import com.latte.admin.web.dto.order.OrderedResponseDto;
import com.latte.admin.web.dto.user.UserJwtResponsetDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.java.Log;

@Log
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderedService orderedService;
    private final CafeService cafeService;
//    private final OrderDetailService orderDetailService;
    private final MenuService menuService;
    private final JwtService jwtService;
    private final UserService userService;
    private final KakaoPayService kakaoPayService;
    private static int TotalPayMoney;
    private static String mainMenuName;
    private static String orderuserID;
    private static Long ooid;

    static void init(){
        TotalPayMoney=0;
        mainMenuName=null;
        orderuserID=null;
        ooid=0L;
    }


    // ooid기준으로 보여주기
    @ApiOperation("ooid기준으로 보여주기")
    @GetMapping("/latte/ordered/{ooid}")
    public Ordered selectAllByOoid(@PathVariable Long ooid) {
        return orderedService.findById(ooid);
    }


    // uuid기준으로 보여주기
    @ApiOperation("uuid기준으로 보여주기")
    @GetMapping("/latte/ordered/{uuid}")
    public List<OrderedResponseDto> selectAllByUuid(@PathVariable Long uuid) {
        return orderedService.selectAllByUuid(uuid);
    }

    // ccid기준으로 보여주기
    @ApiOperation("ccid기준으로 보여주기")
    @GetMapping("/latte/ordered/{ccid}")
    public List<OrderedResponseDto> selectAllByCcid(@PathVariable Long ccid) {
        return orderedService.selectAllByCcid(ccid);
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
    @ApiOperation("유저가 카페에서 메뉴를 주문하는 기능입니다.")
    @PostMapping("/latte/order")
    public Map save(@RequestBody OrderedRequestDto orderedRequestDto, HttpServletRequest httpServletRequest){
        //요청할 때 들어오는 값 :메뉴번호(1개 필수),사이즈번호(1개 필수),옵션번호들(0개~여러개),총 가격- 이 모든게 리스트형태로 들어옴
        String jwt = httpServletRequest.getHeader("Authorization");
        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외

        UserJwtResponsetDto user=jwtService.getUser(jwt);

        Map<String,String> map=new HashMap<>();
        //현재 orderDetailRequestDtos에는 메뉴, 옵션 등의 정보가 담겨 있다.
        //현재 초기 코드는 메뉴만 있다고 가정.

        // 사용자확인
        User orderuser=userService.findByuid(user.getUid());
        // 카페확인
        Cafe ordercafe=cafeService.findByCcId();
        System.out.println("현재 주문하는 유저는 : "+orderuser.getUname()+"님 입니다.");

        Ordered ordered=orderedService.save(orderuser.getUuid(),ordercafe.getCcid());
//        //Ordered 테이블에 먼저 만들어지고나서 orderDetail이 존재할 수 있다.
//        int TotalPay=0;
//        int Totalcnt=0;
//        String mainMenu=null;
//        for(OrderDetailRequestDto odrequset:orderDetailRequestDtos){
//            Long curmmid=odrequset.getMmid();
//            Menu ordermenu=menuService.findById(curmmid);
//            orderDetailService.save(odrequset.toEntity(ordermenu,ordered));
//            System.out.println("orderdetail이 추가되었습니다.");
//            if(mainMenu==null) mainMenu=menuService.findById(odrequset.getMmid()).getMname();
//            //카카오 페이를 위한 준비
//            TotalPay+=odrequset.getPay();
//            Totalcnt++;
//        }

//        //--------------------------위에서는 기존 orderDetail에 관련된 정보만 썼음---------------------
//        //아직 각 메뉴별 사이즈,옵션 정보 사용하지 않은 상태이다. 활용 가능
//        init();
//        orderuserID=orderuser.getUid();
//        ooid=ordered.getOoid();
//        mainMenuName=mainMenu;
//        TotalPayMoney=TotalPay;
//
//       kakaoPayService.kakaoPayReady(ordered.getOoid(),orderuser.getUid(),mainMenu,Totalcnt,TotalPay);
        map.put("result","주문이 완료되었습니다 ^^");
        return map;
    }

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        //프론트에서 이 상태를 봐야함.
        kakaoPayService.kakaoPayInfo(pg_token,ooid,orderuserID,TotalPayMoney);
    }


}
