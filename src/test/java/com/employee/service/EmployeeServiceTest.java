package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.utils.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Doe", savedEmployee.getLastName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployee(1);

        assertEquals("John", foundEmployee.getFirstName());
        assertEquals("Doe", foundEmployee.getLastName());
        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    public void testGetEmployeeNotFound() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> employeeService.getEmployee(1));
        verify(employeeRepository, times(1)).findById(1);
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

        when(employeeRepository.saveAll(anyList())).thenReturn(employees);

        List<Employee> savedEmployees = employeeService.saveAll(employees);

        assertEquals(2, savedEmployees.size());
        verify(employeeRepository, times(1)).saveAll(employees);
    }
}