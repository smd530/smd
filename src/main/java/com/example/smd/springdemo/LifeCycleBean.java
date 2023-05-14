package com.example.smd.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 生命周期
 * @author shanmingda
 */
@Component
public class LifeCycleBean implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);


    public LifeCycleBean() {
        log.debug("构造");
    }

    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String home) {
        log.debug("依赖注入：{}", home);
    }

    @PostConstruct
    public void init() {
        log.debug("初始化");
    }

    @PreDestroy
    public void destroy() {
        log.debug("销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("当前bean " + this + "初始化" );
    }
}
