package com.latte.admin.service;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.cafe.CafeRepository;
import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.menu.MenuRepository;
import com.latte.admin.web.dto.menu.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final CafeRepository cafeRepository;

    // 메뉴 저장
    @Transactional
    public Long save(MenuSaveRequestDto menuSaveRequestDto,Long ccid) {
        Cafe cafe=cafeRepository.findByCcid(ccid);
        return menuRepository.save(menuSaveRequestDto.toEntity(cafe)).getMmid();
    }


    // 카페 기준 모든 메뉴 선택
    @Transactional
    public List<MenuResponseDto> selectAll(Long ccid){
        return menuRepository.findAllByCcid(ccid).stream()
                .map(MenuResponseDto::new)
                .collect(Collectors.toList());
    }


    //Entity형태로 하나 가져올때(Order에서 사용)
    @Transactional
    public Menu findById(Long mmid){
        return menuRepository.findByMmid(mmid);
    }


    // 하나 메뉴 선택
    @Transactional
    public MenuResponseDto selectOne(Long mmid){
        return new MenuResponseDto(menuRepository.findByMmid(mmid));
    }


    // mtype 변화에 따라서 주문페이지에서 메뉴 다르게 보여주기!
    @Transactional
    public List<MenuResponseDto> findByMtype(Long ccid, int mtype) {
        return menuRepository.findByMtype(ccid, mtype).stream()
                .map(MenuResponseDto::new)
                .collect(Collectors.toList());
    }


    // 메뉴명,메뉴 사진 업데이트
    @Transactional
    public Long update(Long mmid, MenuUpdateRequestDto menuUpdateRequestDto) {
        Menu menu = menuRepository.findById(mmid).get();
        //메뉴명, 메뉴사진 업데이트
        menu.update(menuUpdateRequestDto.getMname(),menuUpdateRequestDto.getMpic(),menuUpdateRequestDto.getIsMain(),
                menuUpdateRequestDto.getMtype(),menuUpdateRequestDto.getMprice());

        return mmid;
    }

    // 메뉴 삭제
    @Transactional
    public void delete(Long mmid){
        Menu menu = menuRepository.findById(mmid).get();
        menuRepository.delete(menu);
    }


    // Toogle Main Menu
    @Transactional
    public void toggleMain(Long mmid){
        Menu menu=menuRepository.findByMmid(mmid);
        int toggleValue=0;
        if(menu.getIsMain()==0) toggleValue=1;
        menuRepository.toggleMain(mmid,toggleValue);
    }

}