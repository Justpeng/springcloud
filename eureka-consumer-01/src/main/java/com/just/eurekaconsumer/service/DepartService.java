package com.just.eurekaconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class DepartService {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public List<String> getDeparts() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("com-just-e-provider");
        //url
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer
                .append("http://")
                .append(serviceInstance.getHost())
                .append(":")
                .append(serviceInstance.getPort())
                .append("/depart").append("/names");

        ParameterizedTypeReference<List<String>> typeReference = new ParameterizedTypeReference<List<String>>() {
        };
        ResponseEntity<List<String>> responseEntity = restTemplate.exchange(stringBuffer.toString(), HttpMethod.GET, null, typeReference);
        return responseEntity.getBody();
    }




}
