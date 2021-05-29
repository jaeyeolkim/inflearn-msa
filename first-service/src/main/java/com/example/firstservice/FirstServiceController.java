package com.example.firstservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FirstServiceController {

    private final Environment env;

    @GetMapping("/first-service/welcome")
    public String welcome() {
        return "welcome, first-service";
    }

    @GetMapping("/first-service/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "first service message";
    }

    @GetMapping("/first-service/check")
    public String check() {
        return "CustomFilter second service message";
    }
    @GetMapping("/first-service/env")
    public String env(HttpServletRequest request) {
        log.info("request.getServerPort()={}", request.getServerPort());
        log.info("env.getProperty server port={}", env.getProperty("local.server.port"));
        return String.format("Second service message PORT %s", env.getProperty("local.server.port"));
    }
}
