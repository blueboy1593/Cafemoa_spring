package com.latte.admin.service;

import com.google.gson.internal.$Gson$Preconditions;
import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.cafe.CafeRepository;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.order.OrderedRepository;
import com.latte.admin.domain.user.User;
import com.latte.admin.domain.user.UserRepository;
import com.latte.admin.web.dto.order.OrderedCcidResponseDto;
import com.latte.admin.web.dto.order.OrderedRequestDto;
import com.latte.admin.web.dto.order.OrderedResponseDto;
import com.latte.admin.web.dto.order.OrderedUuidResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderedService {

    private final OrderedRepository orderedRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;

    // 주문번호로 알려주기
    @Transactional
    public Ordered findById(Long ooid){
        return orderedRepository.findByOoid(ooid);
    }

    // user마다 알려주기
    @Transactional
    public List<OrderedUuidResponseDto> selectAllByUid(String uid){
        return orderedRepository.findByUid(uid).stream()
                .map(OrderedUuidResponseDto::new)
                .collect(Collectors.toList());
    }

    // cafe마다 알려주기
    @Transactional
    public List<OrderedCcidResponseDto> selectAllByCcid(Long ccid){
        return orderedRepository.findByCcid(ccid).stream()
                .map(OrderedCcidResponseDto::new)
                .collect(Collectors.toList());
    }

    // 저장
    @Transactional
    public Long save(User orderuser,OrderedRequestDto orderedRequestDto) {
        return orderedRepository.save(orderedRequestDto.toEntity(orderuser)).getOoid();
    }

    // 주문~메뉴나올때까지 상태 변경
    @Transactional
    public void setStatus(int ostatus){
        orderedRepository.setStatus(ostatus);
    }

    //
    @Transactional
    public void searchMyCafe() {}

}
