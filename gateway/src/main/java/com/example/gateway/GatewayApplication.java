package com.example.gateway;

import com.example.gateway.filter.ExampleGatewayFilterFactory;
import com.example.gateway.filter.RateLimitByCpuGatewayFilter;
import com.example.gateway.filter.RateLimitByIpGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Autowired
    private RateLimitByCpuGatewayFilter rateLimitByCpuGatewayFilter;//系统负载分流拦截器限流

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * @Method 使用令牌桶算法进行限流
     * @Author MC
        核心查看RateLimitByIpGatewayFilter类
     * @Return
     * @Date 2019/12/10 0010 16:17
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/customer/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(new RateLimitByIpGatewayFilter(10,1,Duration.ofSeconds(1))))
                        .uri("lb://cloud-consumer")
                        .order(0)
                        .id("throttle_customer_service")
                )
                .build();
    }

    /**
     * @Method 工厂拦截器限流
     * @Author MC

     * @Return
     * @Date 2019/12/10 0010 17:59
     */
    @Bean
    public ExampleGatewayFilterFactory exampleGatewayFilterFactory(){
        return new ExampleGatewayFilterFactory();
    }

    /**
     * @Method 根据系统CPU动态限流
     * @Author MC

     * @Return
     * @Date 2019/12/10 0010 18:00
     */
    @Bean
    public RouteLocator customerCPURouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route(r -> r.path("/customerCPU/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(rateLimitByCpuGatewayFilter))
                        .uri("lb://cloud-consumer")
                        .order(0)
                        .id("customer_CPU_service")
                )
                .build();
        // @formatter:on
    }
}
