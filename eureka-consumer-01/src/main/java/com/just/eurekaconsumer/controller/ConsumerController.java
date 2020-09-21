package com.just.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    //主机名+端口号 -- 改为使用 提供者的为微服务名称
    private static final String SERVICE_PROVIDER = "http://com-just-e-provider/";

    @GetMapping("/msg")
    public String getMsg() {
        return restTemplate.getForObject(SERVICE_PROVIDER + "provider/info", String.class);
    }

}
