package com.latte.admin.web.dto.cafe;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeSaveRequestDto {
    private String cname;
    private String cloc;
    private String cphone;
    private String cpic;
    private String copen;
    private String cclose;
    private String cdesc;
//    private int cstatus;
//    private int coperation;
    private String latitude;
    private String longitude;

    @Builder
    public CafeSaveRequestDto(String cname, String cloc, String cphone, String cpic,String copen,
                              String cclose, String cdesc,String latitude,String longitude) {
        this.cname = cname;
        this.cloc = cloc;
        this.cphone = cphone;
        this.cpic = cpic;
        this.copen = copen;
        this.cclose=cclose;
        this.cdesc=cdesc;
//        this.cstatus = cstatus;
//        this.coperation=coperation;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Cafe toEntity(String uid) {
        return Cafe.builder()
                .cname(cname)
                .cloc(cloc)
                .cphone(cphone)
                .cpic(cpic)
                .copen(copen)
                .cclose(cclose)
                .cdesc(cdesc)
                .latitude(latitude)
                .longitude(longitude)
//                .cstatus(cstatus)
                .uid(uid)
//                .coperation(coperation)
                .build();
    }
}