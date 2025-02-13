package com.employee.controller;

import com.employee.config.GlobalExceptionHandler;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import com.employee.utils.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private GlobalExceptionHandler globalExceptionHandler;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.saveEmployee(employee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John", Objects.requireNonNull(response.getBody()).getFirstName());
        assertEquals("Doe", response.getBody().getLastName());
        verify(employeeService, times(1)).saveEmployee(employee);
    }

    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeService.getEmployee(1)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployee(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", Objects.requireNonNull(response.getBody()).getFirstName());
        assertEquals("Doe", response.getBody().getLastName());
        verify(employeeService, times(1)).getEmployee(1);
    }


    @Test
    public void testGetEmployeeNotFound() {
        when(employeeService.getEmployee(1)).thenThrow(new NotFoundException("Employee object not found for id = 1"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> employeeController.getEmployee(1));

        when(globalExceptionHandler.notFoundExceptionHandler(notFoundException)).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        ResponseEntity<Object> responseEntity = globalExceptionHandler.notFoundExceptionHandler(notFoundException);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(employeeService, times(1)).getEmployee(1);

    }

    @Test
    public void testSaveAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.saveAll(anyList())).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.saveAllEmployee(employees);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(employeeService, times(1)).saveAll(employees);
    }
}