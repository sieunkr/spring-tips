package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Provider;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final Provider<CoffeeRunner> coffeeRunnerProvider;
    private final CoffeeComponent coffeeComponent;
    private final MilkComponent milkComponent;

    public Coffee getCoffee(String name){

        CoffeeRunner coffeeRunner = coffeeRunnerProvider.get();

        coffeeRunner.addChain(coffeeComponent);

        Coffee coffee = new Coffee(name);
        coffeeRunner.accept(coffee);

        return coffee;
    }

}
