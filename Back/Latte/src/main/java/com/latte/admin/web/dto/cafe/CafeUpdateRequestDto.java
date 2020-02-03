package com.latte.admin.web.dto.cafe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeUpdateRequestDto {
    private Long ccid;
    private String cphone;
    private String cpic;
    private String copen;
    private String cclose;
    private String cdesc;
    private int cstatus;  // -1:승인X, 0:대기, 1:승인
    private int coperation;

    @Builder
    public CafeUpdateRequestDto(Long ccid,String cphone,String cpic,String copen,String cclose,
                                String cdesc,int cstatus,int coperation) {
        this.ccid=ccid;
        this.cphone = cphone;
        this.cpic = cpic;
        this.copen = copen;
        this.cclose=cclose;
        this.cdesc=cdesc;
        this.cstatus = cstatus;
        this.coperation=coperation;
    }

    public void updateStatus(Long ccid,int cstatus){
        this.ccid=ccid;
        this.cstatus=cstatus;
    }

}
