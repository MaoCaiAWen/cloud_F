package com.example.consumer6002.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName: cloud
 * @Package: com.example.consumer6002.service
 * @ClassName: SchedualService
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/9 0009 11:50
 * @Version: 1.0
 */
@FeignClient(value = "cloud-producer")
public interface SchedualService {
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    String hello(@RequestParam(value = "name") String name);

}
