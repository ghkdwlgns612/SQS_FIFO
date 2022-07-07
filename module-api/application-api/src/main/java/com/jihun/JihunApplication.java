package com.jihun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JihunApplication {

    public static void main(String[] args) {
        Producer.sendMessage();
        SpringApplication.run(JihunApplication.class,args);
    }
}
