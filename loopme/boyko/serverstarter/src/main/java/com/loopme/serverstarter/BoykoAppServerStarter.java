package com.loopme.serverstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.loopme"})
@EntityScan(basePackages = {"com.loopme.dbmodel"})
public class BoykoAppServerStarter {
    public static void main(String[] args) {
        SpringApplication.run(BoykoAppServerStarter.class, args);
    }
}