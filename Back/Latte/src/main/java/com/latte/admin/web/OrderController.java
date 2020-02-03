package com.latte.admin.web;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.service.MenuService;
import com.latte.admin.service.OrderDetailService;
import com.latte.admin.service.OrderedService;
import com.latte.admin.web.dto.order.OrderDetailRequestDto;
import com.latte.admin.web.dto.order.OrderDetailResponseDto;
import com.latte.admin.web.dto.order.OrderedResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderedService orderedService;
    private final OrderDetailService orderDetailService;
    private final MenuService menuService;

    // 주문메뉴 리스트 보여주기
    @ApiOperation("주문 메뉴 리스트를 보여줍니다.")
    @GetMapping("/latte/orderdetail/{ooid}")
    public List<OrderDetailResponseDto> selectAll(@PathVariable Long ooid) {
        return orderDetailService.findAllDesc(ooid);
    }


    @ApiOperation("현재는 uuid를 pathvariable로 달았지만, 추후 업데이트시 token으로 대체")
    @PostMapping("/latte/ordersave/{uuid}")
    public Map save(@PathVariable Long uuid, @RequestBody List<OrderDetailRequestDto> orderDetailRequestDtos){
        Map<String,String> map=new HashMap<>();
        //현재 orderDetailRequestDtos에는 메뉴, 옵션 등의 정보가 담겨 있다.
        //현재 초기 코드는 메뉴만 있다고 가정.
        Ordered ordered=orderedService.findById(orderedService.save(uuid));
        //Ordered 테이블에 먼저 만들어지고나서 orderDetail이 존재할 수 있다.
        List<Menu> Menus=new ArrayList<>();
        for(OrderDetailRequestDto odrequset:orderDetailRequestDtos)
            Menus.add(menuService.findById(odrequset.getMmid()));
        orderDetailService.save(Menus,ordered);

        map.put("result","주문이 완료되었습니다 ^^");
        return map;
    }
}
