package com.employee.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

@Service
@Slf4j
public class VirtualThreadExecutorLookupService {

    @Autowired
    private JndiTemplate jndiTemplate;
    @Autowired
    private ThreadFactory virtualThreadFactory;
    @Autowired
    private ExecutorService virtualThreadExecutor;


    public void registerVirtualThreadExecutorWithJndi() {
        try {
            log.info("jndi env from registerVirtualThreadExecutorWithJndi: {}", jndiTemplate.getEnvironment());
            jndiTemplate.bind("virtualThreadExecutor", virtualThreadExecutor);
            jndiTemplate.bind("virtualThreadFactory", virtualThreadFactory);
        } catch (NamingException e) {
            throw new RuntimeException("Failed to bind virtualThreadExecutor with Jndi", e);
        }
    }

    public ExecutorService getVirtualThreadExecutorFromJndi() {

        try {
            log.info("jndi env from getVirtualThreadExecutorFromJndi: {}", jndiTemplate.getEnvironment());
            return jndiTemplate.lookup("virtualThreadExecutor", ExecutorService.class);
        } catch (NamingException e) {
            throw new RuntimeException("Failed to bind virtualThreadExecutor with Jndi", e);
        }
    }

    public ThreadFactory getVirtualThreadFactoryFromJndi() {
        try {
            log.info("jndi env from getVirtualThreadFactoryFromJndi: {}", jndiTemplate.getEnvironment());
            return jndiTemplate.lookup("virtualThreadFactory", ThreadFactory.class);
        } catch (NamingException e) {
            throw new RuntimeException("Failed to bind virtualThreadExecutor with Jndi", e);
        }
    }

    public Context getInitialContext() {
        try {
            return jndiTemplate.getContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
