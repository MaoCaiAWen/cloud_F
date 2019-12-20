package com.example.oauthmember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)//会拦截注解了@PreAuthrize注解的配置.
public class OauthMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthMemberApplication.class, args);
    }

}
