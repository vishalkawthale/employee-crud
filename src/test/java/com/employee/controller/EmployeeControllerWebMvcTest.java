package com.employee.controller;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import com.employee.utils.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));

        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    public void testGetEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        when(employeeService.getEmployee(1)).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get/1")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));

        verify(employeeService, times(1)).getEmployee(1);
    }

    @Test
    public void testGetEmployeeNotFound() throws Exception {
        when(employeeService.getEmployee(1)).thenThrow(new NotFoundException("Employee object not found for id = 1"));

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get/1")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(employeeService, times(1)).getEmployee(1);
    }

    @Test
    public void testSaveAllEmployees() throws Exception {
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.saveAll(anyList())).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/saveAll")
                        .contentType("application/json")
                        .content("[{\"firstName\":\"John\",\"lastName\":\"Doe\"},{\"firstName\":\"Jane\",\"lastName\":\"Doe\"}]"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

        verify(employeeService, times(1)).saveAll(anyList());
    }
}
