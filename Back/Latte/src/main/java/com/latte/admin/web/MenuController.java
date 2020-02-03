package com.latte.admin.web;

import com.latte.admin.service.MenuService;
import com.latte.admin.web.dto.menu.MenuResponseDto;
import com.latte.admin.web.dto.menu.MenuSaveRequestDto;
import com.latte.admin.web.dto.menu.MenuUpdateRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @ApiOperation("한 카페가 보유하고있는 모든 메뉴 출력")
    @GetMapping("/latte/menu/all/{ccid}")
    public List<MenuResponseDto> selectAll(@PathVariable Long ccid){
        return menuService.selectAll(ccid);
    }

    @ApiOperation("메뉴 선택시 해당 메뉴 관련 정보 제공(해당 카페, 가격, 이름 사진 등)")
    @GetMapping("/latte/menu/one/{mmid}")
    public MenuResponseDto selectOne(@PathVariable Long mmid){
        return menuService.selectOne(mmid);
    }

    @ApiOperation("카페에서 메뉴 추가시 저장하는 기능")
    @PostMapping("/latte/menu/{ccid}")
    public Map save(@RequestBody MenuSaveRequestDto menuSaveRequestDto, @PathVariable Long ccid) {
        Map<String,String> map=new HashMap<>();

        if(menuService.save(menuSaveRequestDto,ccid)>0) map.put("Result","Success");
        else map.put("Result","Fail");

        return map;
    }

    @ApiOperation("선택된 메뉴를 업데이트 하는 기능")
    @PutMapping("/latte/menu/update/{mmid}")
    public Map update(@RequestBody MenuUpdateRequestDto menuUpdateRequestDto,@PathVariable Long mmid){
        Map<String,Long> map=new HashMap<>();
        map.put("Result",menuService.update(mmid,menuUpdateRequestDto));
        return map;
    }

    @ApiOperation("선택된 메뉴를 삭제하는 기능")
    @DeleteMapping("/latte/menu/delete/{mmid}")
    public void delete(@PathVariable Long mmid){
       menuService.delete(mmid);
    }

}
