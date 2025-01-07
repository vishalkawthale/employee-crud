package com.employee.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

@Component
public class JndiVirtualThreadExecutorRegistrar implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        JndiVirtualThreadExecutorRegistrar.applicationContext = applicationContext;
    }

    public static void registerVirtualThreadExecutorWithJndi() {
        ExecutorService virtualThreadExecutor = applicationContext.getBean("virtualThreadExecutor", ExecutorService.class);
        ThreadFactory virtualThreadFactory = applicationContext.getBean("virtualThreadFactory", ThreadFactory.class);

        Properties properties = new Properties();
        properties.put("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
        JndiTemplate jndiTemplate = new JndiTemplate(properties);

        try {
            jndiTemplate.bind("virtualThreadExecutor", virtualThreadExecutor);
            jndiTemplate.bind("virtualThreadFactory", virtualThreadFactory);
        } catch (NamingException e) {
            throw new RuntimeException("Failed to bind virtualThreadExecutor with Jndi", e);
        }
    }
}
