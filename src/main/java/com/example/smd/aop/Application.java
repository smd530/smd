package com.example.smd.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.annotation.Order;

/**
 * @author shanmingda
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        SaveOrder saveOrder = new SaveOrder();
        saveOrder.setId(1L);
        orderService.saveOrder(saveOrder);

        UpdateOrder updateOrder = new UpdateOrder();
        updateOrder.setOrderId(2L);
        orderService.updateOrder(updateOrder);

    }
}
