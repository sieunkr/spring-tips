package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
@RequestMapping("/delay")
public class DelayController {

    @GetMapping
    public void delay(){

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {

            Thread.sleep(3600000);

            /*
            ##TCP 상태##

            아무것도 안하고 대기하면 ESTABLISHED 상태를 유지
            클라이언트가 강제로 요청을 끊으면 CLOSE_WAIT 상태가 됨.
            (일반적인 웹서비스라면 프로세스가 종료하면서 자동으로 CLOSE_WAIT 가 제거되어야 하지만, sleep 구문으로 인해서 종료가 늦어짐)
            ESTABLISHED 와 CLOSE_WAIT 의 갯수가 max-threads 를 넘어가는 순간, 애플리케이션에서 더이상 요청을 받을 수 없는 상태가 됨

            */

            return "작업 완료";
        });

        doAnotherTask();

        try {

            //String result = future.get();
            String result = future.get(20, TimeUnit.SECONDS);
            System.out.println("result: " + result);
            executor.shutdown();
        }
        catch(TimeoutException e){
            System.out.println("타임 아웃 예외처리");
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doAnotherTask(){
        System.out.println("작업이 진행 중");
    }

}
