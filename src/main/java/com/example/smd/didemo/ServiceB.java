package com.example.smd.didemo;

import com.example.smd.didemo.ServiceA;
import org.springframework.stereotype.Component;

//@Component
public class ServiceB {
    private ServiceA serviceA;

    public ServiceB (ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
