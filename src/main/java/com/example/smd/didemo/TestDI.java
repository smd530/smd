package com.example.smd.didemo;

import com.example.smd.didemo.ServiceAA;
import com.example.smd.didemo.ServiceBB;

public class TestDI {

    public static void main(String[] args) {
//        ServiceB serviceB = new ServiceB(new ServiceA(new ServiceB()));

        ServiceAA serviceAA = new ServiceAA();
        ServiceBB serviceBB = new ServiceBB();
        serviceAA.setServiceBB(serviceBB);
        serviceBB.setServiceAA(serviceAA);


    }

}
