package com.loopme.serverstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.loopme"})
public class BoykoAppServerStarter {
    public static void main(String[] args) {
        SpringApplication.run(BoykoAppServerStarter.class, args);
    }
}