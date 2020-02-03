package com.latte.admin.web;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.service.CafeService;
import com.latte.admin.web.dto.cafe.CafeListResponseDto;
import com.latte.admin.web.dto.cafe.CafeResponseDto;
import com.latte.admin.web.dto.cafe.CafeSaveRequestDto;
import com.latte.admin.web.dto.cafe.CafeUpdateRequestDto;
import com.latte.admin.web.dto.menu.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    // 사장님 기준으로 카페 내용저장 = 추가
    @PostMapping("/latte/cafe/{uuid}")
    public Map save(@PathVariable Long uuid, @RequestBody CafeSaveRequestDto cafeSaveRequestDto){
        Map<String,Long> map=new HashMap<>();
        map.put("result",cafeService.save(uuid, cafeSaveRequestDto));
        return map;
    }

    // 카페 승인상태 변경


    // 카페 리스트 보여주기
    @GetMapping("/latte/cafe/all")
    public List<CafeListResponseDto> selectAll(){
        return cafeService.findAllDesc();
    }

    // ccid로 카페 하나 찾기
    @GetMapping("/latte/cafe/{ccid}")
    public Cafe selectOne(@PathVariable Long ccid){
        return cafeService.findByCcId(ccid);
    }

    // 카페 정보 수정
    @PutMapping("/latte/cafe/update/{ccid}")
    public Map cafeUpdate(@PathVariable Long ccid, @RequestBody CafeUpdateRequestDto cafeUpdateRequestDto) {
        Map<String,Long> map = new HashMap<>();
        map.put("cafe", cafeService.cafeUpdate(ccid, cafeUpdateRequestDto));

        return map;
    }

    // 카페 삭제
    @DeleteMapping("/latte/cafe/delete/{ccid}")
    public void delete(@PathVariable Long ccid) {
        // 세션 해제 추가~!~!~
        cafeService.delete(ccid);
    }

}
