package com.example.consumer6001.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: cloud
 * @Package: com.example.consumer6001.service
 * @ClassName: HelloService
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/9 0009 13:10
 * @Version: 1.0
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String hello(String name) {
        return restTemplate.getForObject("http://producer/get?name="+name, String.class);
    }

    public String error(String name) {
        return "hi,"+name+",sorry,error!";
    }

}
