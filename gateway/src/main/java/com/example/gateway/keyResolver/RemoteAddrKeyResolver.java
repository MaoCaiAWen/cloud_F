package com.example.gateway.keyResolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ProjectName: 自定义Key键解析器，用于路由限流操作
 * @Package: com.example.gateway.keyResolver
 * @ClassName: RemoteAddrKeyResolver
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/10 0010 16:24
 * @Version: 1.0
 */
public class RemoteAddrKeyResolver implements KeyResolver {
    public static final String BEAN_NAME = "remoteAddrKeyResolver";
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
