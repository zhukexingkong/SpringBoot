package com.learn.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimerUtil {
    //@Scheduled(initialDelay = 1000,fixedRate = 1000)
    public void myTask(){
        System.out.println("当前系统时间: " + LocalDateTime.now());
    }
}
