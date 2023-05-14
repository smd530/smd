package com.example.smd.didemo;

import com.example.smd.didemo.ServiceAA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component()
public class ServiceBB {

//    @Autowired
    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA) {
        this.serviceAA = serviceAA;
        System.out.println("ServiceBB 添加AA成功");
    }

    public ServiceBB () {
        System.out.println("suceess.....ServiceBB");
    }
}
