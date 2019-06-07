package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class CoffeeComponent implements CustomConsumer {

    private final MilkComponent milkComponent;

    public CoffeeComponent(MilkComponent milkComponent) {
        this.milkComponent = milkComponent;
    }

    @Override
    public void accept(Coffee coffee) {
        setCoffeeInit(coffee);
        milkComponent.accept(coffee);
    }

    private void setCoffeeInit(Coffee coffee){
        coffee.setName("coffee_" + coffee.getName());
    }

}
