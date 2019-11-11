package com.example.demo1.config;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@ConditionalOnProperty(prefix = "datasource", name = "muti-datasource-open", havingValue = "true")
public class DataSourceAspect implements Ordered {

    @Value("${datasource.default-datasource-name}")
    private String dynamicDataSourceName;

    @Pointcut(value = "@annotation(com.example.demo1.config.DynamicSource)")
    private void dataSource() {

    }

    @Around("dataSource()")
    public Object dataSourceAspect(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        // 需要获取到的是实现类中的具体方法.
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        DynamicSource dynamicSource = currentMethod.getAnnotation(DynamicSource.class);
        if (StringUtils.isNotEmpty(dynamicSource.value())){
            DataSourceContextHolder.setDataSourceType(dynamicSource.value());
        } else {
            DataSourceContextHolder.setDataSourceType(dynamicDataSourceName);
        }

        try {
            return point.proceed();
        } finally {
            // 最后清除数据源
            DataSourceContextHolder.clearDataSourceType();
        }

    }

    /**
     * aop的顺序要早于spring的事务
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
