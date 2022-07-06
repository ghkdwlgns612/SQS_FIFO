package com.example.provider_sqs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/test")
    void test() {
        Client.sendMessage();
    }
}
