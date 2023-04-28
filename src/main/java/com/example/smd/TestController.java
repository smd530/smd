package com.example.smd;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping("fuck")
    @Transactional(readOnly = true, rollbackFor = Exception.class,
            propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public String test () {
        return "fuck it";
    }
}
