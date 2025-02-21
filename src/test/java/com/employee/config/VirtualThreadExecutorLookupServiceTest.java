/*
package com.employee.config;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jndi.JndiTemplate;

import javax.naming.Context;
import javax.naming.NamingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadExecutorLookupServiceTest {

    @Mock
    private JndiTemplate jndiTemplate;

    @Mock
    private ThreadFactory virtualThreadFactory;

    @Mock
    private ExecutorService virtualThreadExecutor;

    @InjectMocks
    private VirtualThreadExecutorLookupService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterVirtualThreadExecutorWithJndi() throws NamingException {
        doNothing().when(jndiTemplate).bind("virtualThreadExecutor", virtualThreadExecutor);
        doNothing().when(jndiTemplate).bind("virtualThreadFactory", virtualThreadFactory);

        service.registerVirtualThreadExecutorWithJndi();

        verify(jndiTemplate).bind("virtualThreadExecutor", virtualThreadExecutor);
        verify(jndiTemplate).bind("virtualThreadFactory", virtualThreadFactory);
    }

    @Test
    public void testGetVirtualThreadExecutorFromJndi() throws NamingException {
        when(jndiTemplate.lookup("virtualThreadExecutor", ExecutorService.class)).thenReturn(virtualThreadExecutor);

        ExecutorService result = service.getVirtualThreadExecutorFromJndi();

        assertEquals(virtualThreadExecutor, result);
    }

    @Test
    public void testGetVirtualThreadFactoryFromJndi() throws NamingException {
        when(jndiTemplate.lookup("virtualThreadFactory", ThreadFactory.class)).thenReturn(virtualThreadFactory);

        ThreadFactory result = service.getVirtualThreadFactoryFromJndi();

        assertEquals(virtualThreadFactory, result);
    }

    @Test
    public void testGetInitialContext() throws NamingException {
        Context context = mock(Context.class);
        when(jndiTemplate.getContext()).thenReturn(context);

        Context result = service.getInitialContext();

        assertEquals(context, result);
    }
}*/
