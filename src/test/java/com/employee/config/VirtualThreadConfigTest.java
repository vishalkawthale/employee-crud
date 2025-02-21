/*
package com.employee.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VirtualThreadConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testVirtualThreadExecutorBean() {
        ExecutorService executorService = (ExecutorService) applicationContext.getBean("virtualThreadExecutor");
        assertNotNull(executorService, "The virtualThreadExecutor bean should not be null");
    }

    @Test
    public void testVirtualThreadFactoryBean() {
        ThreadFactory threadFactory = (ThreadFactory) applicationContext.getBean("virtualThreadFactory");
        assertNotNull(threadFactory, "The virtualThreadFactory bean should not be null");
    }
}*/
