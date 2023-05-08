package com.example.smd.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shanmingda
 */
@Aspect
@Component
public class OperateAspect {

    /**
     * 定义切入点
     * <p>
     * 横切逻辑
     * <p>
     * 织入
     */

    @Pointcut("@annotation(com.example.smd.aop.RecordOperate)")
    public void pointcut() {
    }

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
            1, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        threadPoolExecutor.execute(() -> {
            try {
                MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
                RecordOperate annotation = methodSignature.getMethod().getAnnotation(RecordOperate.class);

                Class<? extends Convert> convert = annotation.convert();
                Convert logConvert = convert.newInstance();
                OperateLogDO operateLogDO = logConvert.covert(proceedingJoinPoint.getArgs()[0]);
                operateLogDO.setDesc(annotation.desc());
                operateLogDO.setResult(result.toString());
                System.out.println("insert operateLog " + operateLogDO);

            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }
}
