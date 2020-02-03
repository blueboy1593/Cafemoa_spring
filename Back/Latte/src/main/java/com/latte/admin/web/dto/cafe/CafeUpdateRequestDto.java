package com.latte.admin.web.dto.cafe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeUpdateRequestDto {
    private String cphone;
    private String cpic;
    private String copen;
    private String cclose;
    private String cdesc;
    private int coperation;

    @Builder
    public CafeUpdateRequestDto(String cphone,String cpic,String copen,String cclose,String cdesc,int coperation) {
        this.cphone = cphone;
        this.cpic = cpic;
        this.copen = copen;
        this.cclose=cclose;
        this.cdesc=cdesc;
        this.coperation=coperation;
    }



}