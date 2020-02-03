package com.latte.admin.web.dto.cafe;

import lombok.Builder;

public class CafeOpenRequestDto {
    private Long ccid;
    private int coperation;

    @Builder
    public CafeOpenRequestDto(Long ccid, int coperation){
        this.ccid=ccid;
        this.coperation=coperation;
    }
}
