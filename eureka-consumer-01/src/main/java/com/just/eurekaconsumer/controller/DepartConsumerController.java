package com.just.eurekaconsumer.controller;

import com.just.eurekaconsumer.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class DepartConsumerController {

    @Autowired
    private DepartService departService;

    @Autowired
    private DiscoveryClient discoveryClient;

    //主机名+端口号 -- 改为使用 提供者的为微服务名称
    private static final String SERVICE_PROVIDER = "http://com-just-e-provider";

    @RequestMapping("/names")
    public List<String> getNames() {
        return departService.getDeparts();
    }


    @GetMapping("/services")
    public List<String> getServices() {
        //获取所有服务列表中的名称，即 spring.application.name的值
        List<String> services = discoveryClient.getServices();
        for (String name : services) {
            //获取指定名称的所有提供者
            List<ServiceInstance> instances = discoveryClient.getInstances(name);
            for (ServiceInstance serviceInstance : instances) {
                //获取服务id,即eureka.instance.instance-id
                String serviceId = serviceInstance.getServiceId();
                URI uri = serviceInstance.getUri();
                String host = serviceInstance.getHost();
                int port = serviceInstance.getPort();
                System.out.println(serviceId + ":" + uri);
                System.out.println(host + ":" + port);
            }
        }
        return services;
    }
}
