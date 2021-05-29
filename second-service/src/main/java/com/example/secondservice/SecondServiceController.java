package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SecondServiceController {

    @GetMapping("/second-service/welcome")
    public String welcome() {
        return "welcome, second-service";
    }

    @GetMapping("/second-service/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "second service message";
    }

    @GetMapping("/second-service/check")
    public String check() {
        return "CustomFilter second service message";
    }
}
