package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Scope("prototype")
public class CoffeeRunner implements CustomConsumer {

    protected List<CustomConsumer> customConsumerList;

    @Override
    public void accept(Coffee coffee) {
        customConsumerList.forEach(e -> e.accept(coffee));
    }

    public void addChain(CustomConsumer consumer){
        this.customConsumerList = new LinkedList<>();

        customConsumerList.add(consumer);
        //customConsumerList.add()
    }

}
