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
    private int cstatus;
    private int coperation;

    @Builder
    public CafeSaveRequestDto(String cname, String cloc, String cphone, String cpic,String copen,
                              String cclose, String cdesc, int cstatus,int coperation) {
        this.cname = cname;
        this.cloc = cloc;
        this.cphone = cphone;
        this.cpic = cpic;
        this.copen = copen;
        this.cclose=cclose;
        this.cdesc=cdesc;
        this.cstatus = cstatus;
        this.coperation=coperation;
    }

    public Cafe toEntity(Long uuid) {
        return Cafe.builder()
                .cname(cname)
                .cloc(cloc)
                .cphone(cphone)
                .cpic(cpic)
                .copen(copen)
                .cclose(cclose)
                .cdesc(cdesc)
                .cstatus(cstatus)
                .coperation(coperation)
                .user(new User(uuid))
                .build();
    }
}
