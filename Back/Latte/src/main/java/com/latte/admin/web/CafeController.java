package com.latte.admin.web;

import com.latte.admin.service.CafeService;
import com.latte.admin.web.dto.cafe.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @PostMapping("/")
    public Map save(HttpServletRequest httpServletRequest, @RequestBody CafeSaveRequestDto cafeSaveRequestDto){
        String jwt=httpServletRequest.getCookies()[0].getValue();
        //유효성 검사
        // 통과

        //파싱 - > 내 정보 가져와ㅣ
        //여기서 uid 넣으면서 쭉쭉


        Map<String,Long> map=new HashMap<>();
        //map.put("result",cafeService.save(uid, cafeSaveRequestDto));
        return map;
    }

    // 카페 대기/승인/거절 상태 변경
    @ApiOperation("[관리자 카페승인 관리페이지]: 카페 대기/승인/거절 상태 변경")
    @PostMapping("/manage/setStatus")
    public int cafeStatusSet(@RequestBody ManageCafeRequestDto manageCafeRequestDto) {
        Long ccid= manageCafeRequestDto.getCcid();
        int cstatus= manageCafeRequestDto.getCstatus();
        cafeService.setStatus(ccid,cstatus);
        return cafeService.findByCcId(ccid).getCstatus();
    }

    // 카페 승인 상태에 따른 리스트 보여주기
    @ApiOperation("[관리자 카페승인관리페이지]:대기,승인,거절을 하나의 페이지에 구분해서 보여줌")
    @PostMapping("/managecafe/{cstatus}")
    public List<CafeListResponseDto> showStatus(@PathVariable int cstatus){
        return cafeService.cafeStatus(cstatus);
    }


    // 카페 실제로 열었는지에 대한 상태 변경
    @ApiOperation("[사장님 카페 관리페이지]: 카페 운영중/운영마감 변경")
    @PostMapping("/opeartion/{ccid}")
    public int cafeOpeartion(@PathVariable Long ccid,@RequestBody CafeOpenRequestDto cafeOpenRequestDto) {
        int coperation=cafeOpenRequestDto.getCoperation();
        return cafeService.findByCcId(ccid).getCoperation();
    }


    // 카페 리스트 보여주기
    @ApiOperation("[손님 카페소개페이지]:카페 리스트를 손님들에게 보여줌-> 실제로 영업중인 것들을 우선적으로 보여줌")
    @GetMapping("/all")
    public List<CafeListResponseDto> selectAll(){
        return cafeService.findByOperation();
    }

    // 카페 정보 수정
    @ApiOperation("[사장님 카페 정보 관리페이지]:특정 카페 정보 수정")
    @PutMapping("/update/{ccid}")
    public Map cafeUpdate(@PathVariable Long ccid, @RequestBody CafeUpdateRequestDto cafeUpdateRequestDto) {
        Map<String,Long> map = new HashMap<>();
        map.put("cafe", cafeService.cafeUpdate(ccid, cafeUpdateRequestDto));

        return map;
    }

    // ccid로 카페 하나 찾기 -> cafe + menu
    @ApiOperation("[손님 카페Detail페이지]:ccid를 기준으로 하나의 카페 정보 찾기")
    @GetMapping("/{ccid}")
    public CafeDetailForGUEST selectOne(@PathVariable Long ccid) {
        return new CafeDetailForGUEST(cafeService.findByCcId(ccid));
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