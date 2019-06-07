package com.example.demo;

import lombok.Data;

@Data
public class Coffee {

    private String name;
    private Boolean isMilk;
    private Integer price;

    public Coffee(){
    }

    public Coffee(String name){
        this.name = name;
    }

}
