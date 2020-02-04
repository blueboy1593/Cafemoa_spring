package com.latte.admin.web;

import com.latte.admin.service.MenuService;
import com.latte.admin.web.dto.menu.MenuResponseDto;
import com.latte.admin.web.dto.menu.MenuSaveRequestDto;
import com.latte.admin.web.dto.menu.MenuUpdateRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/latte/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // 메뉴 저장
    @ApiOperation("[사장님 페이지]:카페에서 메뉴 추가시 저장하는 기능")
    @PostMapping("/{ccid}")
    public Map save(@RequestBody MenuSaveRequestDto menuSaveRequestDto,@PathVariable Long ccid) {
        Map<String,Long> map=new HashMap<>();
        map.put("Result",menuService.save(menuSaveRequestDto,ccid));

        return map;
    }

    // 한 카페가 보유하고있는 모든 메뉴 출력
    @ApiOperation("[주문페이지/카페소개도?]:한 카페가 보유하고있는 모든 메뉴 출력")
    @GetMapping("/all/{ccid}")
    public List<MenuResponseDto> selectAll(@PathVariable Long ccid){
        return menuService.selectAll(ccid);
    }

    // 메뉴 선택시 해당 메뉴 관련 정보 제공(해당 카페, 가격, 이름 사진 등)
    @ApiOperation("[언제사용..?]: 메뉴 선택시 해당 메뉴 관련 정보 제공(해당 카페, 가격, 이름 사진 등)")
    @GetMapping("/one/{mmid}")
    public MenuResponseDto selectOne(@PathVariable Long mmid){
        return menuService.selectOne(mmid);
    }

    // menu update
    @ApiOperation("[사장님페이지]: 메뉴를 업데이트 하는 기능")
    @PutMapping("/update/{mmid}")
    public Map update(@RequestBody MenuUpdateRequestDto menuUpdateRequestDto,@PathVariable Long mmid){
        Map<String,Long> map=new HashMap<>();
        map.put("Result",menuService.update(mmid,menuUpdateRequestDto));
        return map;
    }

    // menu delete
    @ApiOperation("[사장님페이지]:선택된 메뉴를 삭제하는 기능")
    @DeleteMapping("/delete/{mmid}")
    public void delete(@PathVariable Long mmid){
        menuService.delete(mmid);
    }

}