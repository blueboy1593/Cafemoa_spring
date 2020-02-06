package com.latte.admin.service;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.menu.MenuRepository;
import com.latte.admin.domain.menu.MenuSize;
import com.latte.admin.domain.menu.MenuSizeRepository;
import com.latte.admin.domain.options.Option;
import com.latte.admin.domain.options.OptionRepository;
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
    private final MenuSizeRepository menuSizeRepository;
    private final OptionRepository optionRepository;

    // 메뉴 저장
    @Transactional
    public Long save(MenuSaveRequestDto menuSaveRequestDto,Long ccid) {
        return menuRepository.save(menuSaveRequestDto.toEntity(ccid)).getMmid();
    }

    //메뉴사이즈를 저장합니다.
    @Transactional
    public void MenuSizesave(MenuSize menuSize){
        menuSizeRepository.save(menuSize);
    }


    //옵션을 저장합니다.
    @Transactional
    public void Optionsave(MenuOptionRequestDto menuOptionRequestDto,Menu menu){
        optionRepository.save(menuOptionRequestDto.toEntity(menu));
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


    // 메뉴명,메뉴 사진 업데이트
    @Transactional
    public Long update(Long mmid, MenuUpdateRequestDto menuUpdateRequestDto) {
        Menu menu = menuRepository.findById(mmid).get();
        //메뉴명, 메뉴사진 업데이트
        menu.update(menuUpdateRequestDto.getMname(),menuUpdateRequestDto.getMpic());
        return mmid;
    }

    // 각 메뉴별 사이즈 업데이트
    @Transactional
    public Long updateSize(Long mmid, MenuSizeUpdateDto menuSizeUpdateDto) {
        Menu menu = menuRepository.findById(mmid).get();
        MenuSize menuSize= menuSizeRepository.findById(menuSizeUpdateDto.getMsid()).get();
        menuSize.update(menuSizeUpdateDto.getMsname(),menuSizeUpdateDto.getMsprice());
        return menuSize.getMsid();
    }

    // 각 메뉴별 옵션 업데이트
    @Transactional
    public Long updateOption(Long mmid, MenuOptionUpdateRequestDto menuOptionUpdateRequestDto) {
        Menu menu = menuRepository.findById(mmid).get();
        Option option=optionRepository.findById(menuOptionUpdateRequestDto.getOpid()).get();
        option.update(menuOptionUpdateRequestDto.getOptionName(),menuOptionUpdateRequestDto.getOptionPrice());
        return option.getOpid();
    }


    // 메뉴 삭제
    @Transactional
    public void delete(Long mmid){
        Menu menu = menuRepository.findById(mmid).get();
        menuRepository.delete(menu);
    }

    // 사이즈 삭제
    @Transactional
    public void deleteSize(Long msid){
        MenuSize menuSize = menuSizeRepository.findById(msid).get();
        menuSizeRepository.delete(menuSize);
    }

    // 옵션 삭제
    @Transactional
    public void deleteOption(Long opid){
        Option option=optionRepository.findById(opid).get();
        optionRepository.delete(option);
    }

}