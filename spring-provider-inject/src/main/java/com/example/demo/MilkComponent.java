package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class MilkComponent implements CustomConsumer {

    @Override
    public void accept(Coffee coffee) {
        coffee.setIsMilk(true);
    }
}
