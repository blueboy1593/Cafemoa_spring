package com.latte.admin.web.dto.cafe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeUpdateRequestDto {
    private String uid; //이건 프론트쪽에서 그대로 넘겨줘야함.
    private String cname;
    private String cloc;
    private String cphone;
    private String cpic;
    private String copen;
    private String cclose;
    private String cdesc;
    private String latitude;
    private String longitude;

    @Builder
    public CafeUpdateRequestDto(String uid,String cname,String cloc,String cphone,String cpic,
                                String copen,String cclose,String cdesc,String latitude,String longitude){
        this.uid=uid;
        this.cname=cname;
        this.cloc=cloc;
        this.cphone=cphone;
        this.cpic=cpic;
        this.copen=copen;
        this.cclose=cclose;
        this.cdesc=cdesc;
        this.latitude=latitude;
        this.longitude=longitude;
    }
}