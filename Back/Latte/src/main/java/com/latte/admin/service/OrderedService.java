package com.latte.admin.service;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.cafe.CafeRepository;
import com.latte.admin.domain.order.Ordered;
import com.latte.admin.domain.order.OrderedRepository;
import com.latte.admin.domain.user.User;
import com.latte.admin.domain.user.UserRepository;
import com.latte.admin.web.dto.order.OrderedRequestDto;
import com.latte.admin.web.dto.order.OrderedResponseDto;
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
    public List<OrderedResponseDto> selectAllByUuid(Long uuid){
        return orderedRepository.findByUuid(uuid).stream()
                .map(OrderedResponseDto::new)
                .collect(Collectors.toList());
    }


    // cafe마다 알려주기
    @Transactional
    public List<OrderedResponseDto> selectAllByCcid(Long ccid){
        return orderedRepository.findByCcid(ccid).stream()
                .map(OrderedResponseDto::new)
                .collect(Collectors.toList());
    }



    // 저장
    @Transactional
    public Long save(Long uuid, Long ccid) {
        User orderuser=userRepository.findById(uuid).get();
        Cafe ordercafe=cafeRepository.findById(ccid).get();

        OrderedRequestDto orderedRequestDto=new OrderedRequestDto();
        return orderedRepository.save(orderedRequestDto.toEntity(orderuser,ordercafe)).getOoid();
    }

    // 주문~메뉴나올때까지 상태 변경
    @Transactional
    public void setStatus(int ostatus){
        orderedRepository.setStatus(ostatus);
    }

}
