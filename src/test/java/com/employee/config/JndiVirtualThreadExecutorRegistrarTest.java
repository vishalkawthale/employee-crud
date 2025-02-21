/*
package com.employee.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JndiVirtualThreadExecutorRegistrar.class, JndiTemplate.class})
public class JndiVirtualThreadExecutorRegistrarTest {

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private ExecutorService virtualThreadExecutor;

    @Mock
    private ThreadFactory virtualThreadFactory;

    @InjectMocks
    private JndiVirtualThreadExecutorRegistrar registrar;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registrar.setApplicationContext(applicationContext);
    }

    @Test
    public void testRegisterVirtualThreadExecutorWithJndi_Success() throws Exception {
        when(applicationContext.getBean("virtualThreadExecutor", ExecutorService.class)).thenReturn(virtualThreadExecutor);
        when(applicationContext.getBean("virtualThreadFactory", ThreadFactory.class)).thenReturn(virtualThreadFactory);

        JndiTemplate jndiTemplate = mock(JndiTemplate.class);
        Properties properties = new Properties();
        properties.put("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
        whenNew(JndiTemplate.class).withArguments(properties).thenReturn(jndiTemplate);

        JndiVirtualThreadExecutorRegistrar.registerVirtualThreadExecutorWithJndi();

        verify(jndiTemplate).bind("virtualThreadExecutor", virtualThreadExecutor);
        verify(jndiTemplate).bind("virtualThreadFactory", virtualThreadFactory);
    }

    @Test
    public void testRegisterVirtualThreadExecutorWithJndi_NamingException() throws Exception {
        when(applicationContext.getBean("virtualThreadExecutor", ExecutorService.class)).thenReturn(virtualThreadExecutor);
        when(applicationContext.getBean("virtualThreadFactory", ThreadFactory.class)).thenReturn(virtualThreadFactory);

        JndiTemplate jndiTemplate = mock(JndiTemplate.class);
        Properties properties = new Properties();
        properties.put("java.naming.factory.initial", "org.apache.naming.java.javaURLContextFactory");
        whenNew(JndiTemplate.class).withArguments(properties).thenReturn(jndiTemplate);

        doThrow(new NamingException()).when(jndiTemplate).bind("virtualThreadExecutor", virtualThreadExecutor);

        assertThrows(RuntimeException.class, JndiVirtualThreadExecutorRegistrar::registerVirtualThreadExecutorWithJndi);
    }
}*/
