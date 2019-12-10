package com.example.gateway.config;

import com.example.gateway.keyResolver.RemoteAddrKeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @ProjectName: 设置限流的策略
 * @Package: com.example.gateway.config
 * @ClassName: RequestRateLimiterConfig
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/10 0010 10:42
 * @Version: 1.0
 */

@Configuration
public class RequestRateLimiterConfig {

    @Bean
    KeyResolver userKeyResolver() {
        //根据请求参数中的 user 字段来限流
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }

    @Bean
    public KeyResolver ipKeyResolver() {
        //根据请求 IP 地址来限流
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    @Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
    public RemoteAddrKeyResolver remoteAddrKeyResolver() {
        return new RemoteAddrKeyResolver();
    }
}
