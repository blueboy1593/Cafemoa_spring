package com.latte.admin.service;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.cafe.CafeRepository;
import com.latte.admin.web.dto.cafe.CafeListResponseDto;
import com.latte.admin.web.dto.cafe.CafeUpdateRequestDto;
import com.latte.admin.web.dto.cafe.CafeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;

    // cafe정보 저장
    @Transactional
    public Long save(Long uuid, CafeSaveRequestDto cafeSaveRequestDto){
        return cafeRepository.save(cafeSaveRequestDto.toEntity(uuid)).getCcid();
    }

   // 승인 카페 리스트(대기, 승인, 거절) -> 관리자페이지
    @Transactional
    public List<CafeListResponseDto> cafeStatus(int cstatus) {
        return cafeRepository.cafeStatus(cstatus).stream()
                .map(CafeListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 카페 리스트 다 보여주기 -> 손님: 운영중인 카페
    @Transactional
    public List<CafeListResponseDto> findByOperation() {
        return cafeRepository.findAllByDesc().stream()
                .map(CafeListResponseDto::new)
                .collect(Collectors.toList());
    }

    // ccid로 카페 하나 찾기
    @Transactional
    public Cafe findByCcId(Long ccid){
        return cafeRepository.findByCcid(ccid);
    }

    // 카페 정보 수정
    @Transactional
    public Long cafeUpdate(Long ccid, CafeUpdateRequestDto cafeUpdateRequestDto) {
        Cafe cafe= cafeRepository.findById(ccid).orElseThrow(()
                -> new IllegalArgumentException("해당 사용자가 없습니다."));

        cafe.CafeUpdate(cafeUpdateRequestDto.getCphone(),cafeUpdateRequestDto.getCpic(),cafeUpdateRequestDto.getCopen(),
                cafeUpdateRequestDto.getCclose(),cafeUpdateRequestDto.getCdesc(),cafeUpdateRequestDto.getCstatus(),cafeUpdateRequestDto.getCoperation());

        return ccid;
    }

//    // 탈퇴 = 카페 삭제
//    @Transactional
//    public void delete(Long ccid){
//        Cafe cafe= cafeRepository.findById(ccid)
//                .orElseThrow(()-> new IllegalArgumentException("카페 정보가 없습니다."));
//
//        cafeRepository.delete(cafe);
//    }
}
