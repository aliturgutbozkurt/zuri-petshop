package com.turkninja.petshop.exception;

import lombok.Data;

@Data
public class AppMessageDescription {

    public AppMessageDescription(String tr, String en){
        this.tr = tr;
        this.en = en;
    }

    private String tr;

    private String en;

    public String toString(){
        return "Tr : " + getTr() +" En :" + getEn();
    }


}
