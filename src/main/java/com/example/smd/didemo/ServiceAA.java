package com.example.smd.didemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceAA {

    @Autowired
    private ServiceBB serviceBB;

    public void setServiceBB (ServiceBB serviceBB) {
        this.serviceBB = serviceBB;
        System.out.println("ServiceAA 添加BB成功");
    }

    public ServiceAA () {
        System.out.println("suceess.....ServiceAA");
    }
}
