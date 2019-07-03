package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Component
public class DelayService {

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    ForkJoinPool customThreadPool = new ForkJoinPool(30);

    private List<String> testList;

    public DelayService() {
        this.testList = Arrays.asList("a","b","c","d","e");
    }

    public List<String> delay(){
        try {
            return findByName();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> findByName() throws InterruptedException, ExecutionException, TimeoutException {

        return customThreadPool.submit(()-> testList.parallelStream()
        //return threadPoolTaskExecutor.submit(()-> testList.parallelStream()
                .map(p -> {
                    System.out.println("####" + Thread.currentThread().getName());
                    return upper(p);
                })
                .collect(Collectors.toList())).get(10000, TimeUnit.SECONDS);
    }

    private String upper(String p){

        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return p.toUpperCase();
    }
}
