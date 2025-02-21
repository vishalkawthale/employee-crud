package com.employee.config;

import com.employee.utils.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testWebExchangeBindExceptionHandler() {
        WebExchangeBindException ex = mock(WebExchangeBindException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(ex.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(new ObjectError("test", "error message")));

        ResponseEntity<Object> response = globalExceptionHandler.webExchangeBindExceptionHandler(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        //assertEquals("error message", ((Set<?>) response.getBody().get("message")).iterator().next());
    }

    @Test
    void testMethodArgumentNotValidExceptionHandler() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(ex.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(new ObjectError("test", "error message")));

        ResponseEntity<Object> response = globalExceptionHandler.methodArgumentNotValidExceptionHandler(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        //assertEquals("error message", ((Set<?>) response.getBody().get("message")).iterator().next());
    }

    @Test
    void testConstraintViolationExceptionHandler() {
        ConstraintViolationException ex = mock(ConstraintViolationException.class);
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("violation message");
        when(ex.getConstraintViolations()).thenReturn(Collections.singleton(violation));

        ResponseEntity<Object> response = globalExceptionHandler.constraintViolationExceptionHandler(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        //assertEquals("violation message", ((Set<?>) response.getBody().get("message")).iterator().next());
    }

    @Test
    void testNotFoundExceptionHandler() {
        NotFoundException ex = new NotFoundException("not found");

        ResponseEntity<Object> response = globalExceptionHandler.notFoundExceptionHandler(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        //assertEquals("not found", ((Set<?>) response.getBody().get("message")).iterator().next());
    }
}