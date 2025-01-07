package com.employee;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.employee")
@Slf4j
public class EmployeeCrudApplication {

    private static final Logger log = LoggerFactory.getLogger(EmployeeCrudApplication.class);

    public static void main(String[] args) {
        log.info("EmployeeCrudApplication: {}", Thread.currentThread());
        SpringApplication.run(EmployeeCrudApplication.class, args);
    }

    //@Autowired
    //private VirtualThreadExecutorLookupService lookupService;

    /*@Override
    public void run(String... args) throws Exception {
        //JndiVirtualThreadExecutorRegistrar.registerVirtualThreadExecutorWithJndi();
        //DefaultManagedTaskExecutor
        lookupService.registerVirtualThreadExecutorWithJndi();
        log.info("jndi template executor: {}", lookupService.getVirtualThreadExecutorFromJndi());
        //log.info("jndi Initial Context executor: {}", (Object) InitialContext.doLookup("java:comp/env/virtualThreadExecutor"));
        //lookupService.getInitialContext().getEnvironment();
        log.info("context env map before: {}", lookupService.getInitialContext().getEnvironment());
        Context initialContext = lookupService.getInitialContext();
        initialContext.addToEnvironment(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        log.info("context env map after: {}", lookupService.getInitialContext().getEnvironment());
        log.info("jndi Initial Context executor: {}", (Object) InitialContext.doLookup("virtualThreadExecutor"));
    }*/
}
