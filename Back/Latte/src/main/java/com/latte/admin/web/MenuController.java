package com.latte.admin.web;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.service.MenuService;
import com.latte.admin.web.dto.menu.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping("/latte/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // 메뉴저장
    @ApiOperation("[사장님 페이지]:카페에서 메뉴 추가시 저장하는 기능")
    @PostMapping("/{ccid}")
    public Map save(@PathVariable Long ccid, @RequestBody MenuSaveRequestDto menuSaveRequestDto) {
        // 메뉴
        Map<String,String> map=new HashMap<>();
        Long mmid=menuService.save(menuSaveRequestDto,ccid); //메뉴 save -> mmid가 나와.
        Menu menu=menuService.findById(mmid);

        map.put("result","메뉴가 저장되었습니다~");
        return map;
    }

    
    // 한 카페가 보유하고있는 모든 메뉴 출력
    @ApiOperation("[주문페이지/카페소개도?]:한 카페가 보유하고있는 모든 메뉴 출력")
    @GetMapping("/{ccid}")
    public List<MenuResponseDto> selectAll(@PathVariable Long ccid){
        return menuService.selectAll(ccid);
    }

    // mtype 변화에 따라서 주문페이지에서 메뉴 다르게 보여주기!
    @ApiOperation("mtype 변화에 따라서 주문페이지에서 type별로 다르게 보여주기!")
    @GetMapping("/{ccid}/{mtype}")
    public List<MenuResponseDto> findByMtype(@PathVariable Long ccid,@PathVariable int mtype){
        return menuService.findByMtype(ccid,mtype);
    }

    // 메뉴 선택시 해당 메뉴 관련 정보 제공(해당 카페, 가격, 이름 사진 등)
    @ApiOperation("[언제사용..?]: 메뉴 선택시 해당 메뉴 관련 정보 제공(해당 카페, 가격, 이름 사진 등)")
    @GetMapping("/one/{mmid}")
    public MenuResponseDto selectOne(@PathVariable Long mmid){
        return menuService.selectOne(mmid);
    }

    // jwt 필요
    // menu update(한번에 옵션, 사이즈 메뉴정보 다 할거임)
    @ApiOperation("[사장님페이지]: 메뉴를 업데이트 하는 기능")
    @PutMapping("/update/{mmid}")
    public Map update(@RequestBody MenuUpdateRequestDto menuUpdateRequestDto,@PathVariable Long mmid){
        Map<String,String> map=new HashMap<>();
        menuService.update(mmid,menuUpdateRequestDto); //메뉴이름 , 사진 업데이트
        map.put("result" , "메뉴 수정이 완료되었습니다~");
        return map;
    }

    // menu delete
    @ApiOperation("[사장님페이지]:선택된 메뉴를 삭제하는 기능")
    @DeleteMapping("/delete/{mmid}")
    public void delete(@PathVariable Long mmid){
        menuService.delete(mmid);
    }


    @ApiOperation("메인메뉴 설정/취소(버튼으로 토글)")
    @PutMapping("/toggleMain/{mmid}")
    public void toggleMain(@PathVariable Long mmid){
        menuService.toggleMain(mmid);
    }

}