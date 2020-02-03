package com.latte.admin.web.dto.cafe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ManageCafeRequestDto {
    private Long ccid;
    private int cstatus;

    @Builder
    public ManageCafeRequestDto(Long ccid, int cstatus){
        this.ccid=ccid;
        this.cstatus=cstatus;
    }
}