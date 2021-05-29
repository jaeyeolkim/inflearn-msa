package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Pre filter
        return ((exchange, chain) -> {
            // Netty 비동기 방식 서버 사용시에는 ServerHttpRequest 를 사용해야 한다.
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("[CustomFilter] PRE Filter = {}", request.getId());

            // Post Filter
            // 비동기 방식의 단일값 전달시 Mono 사용(Webflux)
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("[CustomFilter] POST Filter = {}", response.getStatusCode());
            }));
        });
    }

    public static class Config {
        // put the configure
    }
}
