package com.latte.admin.web;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.service.CafeService;
import com.latte.admin.web.dto.cafe.CafeListResponseDto;
import com.latte.admin.web.dto.cafe.CafeResponseDto;
import com.latte.admin.web.dto.cafe.CafeSaveRequestDto;
import com.latte.admin.web.dto.cafe.CafeUpdateRequestDto;
import com.latte.admin.web.dto.manageCafe.manageCafeRequest;
import com.latte.admin.web.dto.menu.MenuResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/latte/cafe")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    // 사장님 기준으로 카페 내용저장 = 추가
    @ApiOperation("[사장님 회원가입페이지]: 회원가입 시 카페 내용 저장")
    @PostMapping("/{uuid}")
    public Map save(@PathVariable Long uuid, @RequestBody CafeSaveRequestDto cafeSaveRequestDto){
        Map<String,Long> map=new HashMap<>();
        map.put("result",cafeService.save(uuid, cafeSaveRequestDto));
        return map;
    }

    // 카페 승인 상태에 따른 리스트 보여주기
    @ApiOperation("[관리자 카페승인관리페이지]:대기,승인,거절을 하나의 페이지에 구분해서 보여줌")
    @PostMapping("/managecafe/{cstatus}")
    public List<CafeListResponseDto> showStatus(@PathVariable int cstatus){
        return cafeService.cafeStatus(cstatus);
    }

    // 카페 대기/승인/거절 상태 변경
    @ApiOperation("[관리자 카페승인 관리페이지]: 카페 대기/승인/거절 상태 변경")
    @PostMapping("/manage/setStatus")
    public int cafeStatusSet(@RequestBody manageCafeRequest manageCafeRequest) {
        Cafe cafe=cafeService.findByCcId(manageCafeRequest.getCcid());
        cafe.statusSet(manageCafeRequest.getCstatus());
        return cafe.getCstatus();
    }

    // 카페 리스트 보여주기
    @ApiOperation("[손님 카페소개페이지]:카페 리스트를 손님들에게 보여줌-> 실제로 영업중인 것들을 우선적으로 보여줌")
    @GetMapping("/all")
    public List<CafeListResponseDto> selectAll(){
        return cafeService.findByOperation();
    }

    // ccid로 카페 하나 찾기
    @ApiOperation("[손님 카페Detail페이지]:ccid를 기준으로 하나의 카페 정보 찾기")
    @GetMapping("/{ccid}")
    public Cafe selectOne(@PathVariable Long ccid){
        return cafeService.findByCcId(ccid);
    }

    // 카페 정보 수정
    @ApiOperation("[사장님 카페 정보 관리페이지]:특정 카페 정보 수정")
    @PutMapping("/update/{ccid}")
    public Map cafeUpdate(@PathVariable Long ccid, @RequestBody CafeUpdateRequestDto cafeUpdateRequestDto) {
        Map<String,Long> map = new HashMap<>();
        map.put("cafe", cafeService.cafeUpdate(ccid, cafeUpdateRequestDto));

        return map;
    }
//
//    // 카페 삭제 = 탈퇴
//    @ApiOperation("[사장님 카페 정보 관리페이지]:특정 카페 삭제")
//    @DeleteMapping("/delete/{ccid}")
//    public void delete(@PathVariable Long ccid) {
//        // 세션 해제 추가~!~!~~!~!~~!~!~~!~!~~!~!~~!~!~~!~!~~!~!~~!~!~~!~!~~!~!~~!~!~~!~!~
//        cafeService.delete(ccid);
//    }

}
