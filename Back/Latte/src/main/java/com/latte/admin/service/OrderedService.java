package com.latte.admin.service;

import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.order.OrderedRepository;
import com.latte.admin.domain.user.User;
import com.latte.admin.domain.user.UserRepository;
import com.latte.admin.web.dto.order.OrderedRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderedService {

    private final OrderedRepository orderedRepository;
    private final UserRepository userRepository;

    @Transactional
    public Ordered findById(Long ooid){
        return orderedRepository.findById(ooid).get();
    }


    // 저장
    @Transactional
    public Long save(User orderuser) {
        OrderedRequestDto orderedRequestDto=new OrderedRequestDto();
        return orderedRepository.save(orderedRequestDto.toEntity(orderuser)).getOoid();
    }

    // 주문~메뉴나올때까지 상태 변경
    @Transactional
    public void setStatus(int ostatus){
        orderedRepository.setStatus(ostatus);
    }

}
