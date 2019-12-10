package com.example.gateway.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: 令牌桶算法拦截器
 * 在这个实现中，我们使用了 IP 来进行限制，当达到最大流量就返回 429 错误。
 * 这里我们简单使用一个 Map 来存储 bucket，所以也决定了它只能单点使用，如果是分布式的话，
 * 可以采用 Hazelcast 或 Redis 等解决方案。
 * @Package: com.example.gateway.filter
 * @ClassName: RateLimitByIpGatewayFilter
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/10 0010 15:53
 * @Version: 1.0
 */
public class RateLimitByIpGatewayFilter implements GatewayFilter, Ordered {
    private static final Log log = LogFactory.getLog(GatewayFilter.class);
        int capacity;//桶的最大容量，即能装载 Token 的最大数量
        int refillTokens;//每次 Token 补充量
        Duration refillDuration;//补充 Token 的时间间隔

        private static final Map<String, Bucket> CACHE = new ConcurrentHashMap<>();

    public RateLimitByIpGatewayFilter(int capacity, int refillTokens, Duration refillDuration) {
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.refillDuration = refillDuration;
    }

    private Bucket createNewBucket() {
            Refill refill = Refill.of(refillTokens,refillDuration);
            Bandwidth limit = Bandwidth.classic(capacity,refill);
            return Bucket4j.builder().addLimit(limit).build();
        }


        @Override
        public Mono<Void> filter(ServerWebExchange exchange,GatewayFilterChain chain) {
            // if (!enableRateLimit){
            //     return chain.filter(exchange);
            // }
            String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            Bucket bucket = CACHE.computeIfAbsent(ip,k -> createNewBucket());

            log.debug("IP: " + ip + "，TokenBucket Available Tokens: " + bucket.getAvailableTokens());
            if (bucket.tryConsume(1)) {
                return chain.filter(exchange);
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                return exchange.getResponse().setComplete();
            }
        }

    @Override
        public int getOrder() {
            return -1000;
        }
}
