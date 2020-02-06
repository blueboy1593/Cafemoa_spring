package com.latte.admin.domain.options;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.latte.admin.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Option {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long opid;

    @Column
    String OptionName;

    @Column
    int OptionPrice;

    @ManyToOne
    @JsonBackReference
    Menu optionmenu;

    @Builder
    public Option(String OptionName,int OptionPrice,Menu optionmenu){
        this.OptionName=OptionName;
        this.OptionPrice=OptionPrice;
        this.optionmenu=optionmenu;
    }

    public void update(String OptionName,int OptionPrice){
        this.OptionName=OptionName;
        this.OptionPrice=OptionPrice;
    }

}
