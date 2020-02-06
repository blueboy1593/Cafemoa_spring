package com.latte.admin.web.dto.menu;

import com.latte.admin.domain.menu.Menu;
import com.latte.admin.domain.options.Option;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuOptionRequestDto {
    private String optionName;
    private int optionPrice;

    @Builder
    public MenuOptionRequestDto(String optionName, int optionPrice) {
        this.optionName=optionName;
        this.optionPrice=optionPrice;
    }

    public Option toEntity(Menu menu){
        return Option.builder()
                .OptionName(optionName)
                .OptionPrice(optionPrice)
                .optionmenu(menu)
                .build();
    }

}
