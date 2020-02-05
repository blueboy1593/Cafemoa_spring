package com.latte.admin.service;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.menu.MenuRepository;
import com.latte.admin.domain.user.User;
import com.latte.admin.web.dto.cafe.CafeListResponseDto;
import com.latte.admin.web.dto.menu.MenuResponseDto;
import com.latte.admin.web.dto.menu.MenuSaveRequestDto;
import com.latte.admin.web.dto.menu.MenuUpdateRequestDto;
import com.latte.admin.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    // 카페기준 저장
    @Transactional
    public Long save(MenuSaveRequestDto menuSaveRequestDto,Long ccid) {
        return menuRepository.save(menuSaveRequestDto.toEntity(ccid)).getMmid();
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


    // 메뉴 업데이트
    @Transactional
    public Long update(Long mmid, MenuUpdateRequestDto menuUpdateRequestDto) {
        Menu menu = menuRepository.findById(mmid).orElseThrow(()
                -> new IllegalArgumentException("해당 사용자가 없습니다."));

        menu.update(menuUpdateRequestDto.getMname(), menuUpdateRequestDto.getMprice(),
                menuUpdateRequestDto.getMpic());

        return mmid;
    }

    // 메뉴 삭제
    @Transactional
    public void delete(Long mmid){
        Menu menu = menuRepository.findById(mmid).orElseThrow(()
                -> new IllegalArgumentException("해당 사용자가 없습니다."));
        menuRepository.delete(menu);
    }
}