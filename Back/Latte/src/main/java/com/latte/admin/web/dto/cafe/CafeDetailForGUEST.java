package com.latte.admin.web.dto.cafe;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.menu.Menu;
import com.latte.admin.web.dto.menu.MenuResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CafeDetailForGUEST {
    private String cname;
    private String cloc;
    private String cphone;
    private String cpic;
    private String copen;
    private String cclose;
    private String cdesc;
    private int cstatus;
    private int coperation;
    private String latitude;
    private String longitude;
    private List<MenuResponseDto> menus=new ArrayList<>();

    @Builder
    public CafeDetailForGUEST(Cafe entity) {
        this.cname = entity.getCname();
        this.cloc = entity.getCloc();
        this.cphone = entity.getCphone();
        this.cpic = entity.getCpic();
        this.copen = entity.getCopen();
        this.cclose = entity.getCclose();
        this.cdesc = entity.getCdesc();
        this.cstatus = entity.getCstatus();
        this.coperation=entity.getCoperation();
        this.latitude=entity.getLatitude();
        this.longitude=entity.getLongitude();
        for(Menu m:entity.getMenus()){
            menus.add(new MenuResponseDto(m));
        }
    }
}
