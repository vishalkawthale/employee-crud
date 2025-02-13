package com.employee.repository;

import com.employee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveAndFindById() {
        // Create a new employee
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Engineering");

        // Save the employee
        Employee savedEmployee = employeeRepository.save(employee);

        // Find the employee by ID
        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        // Verify the employee was found
        assertThat(foundEmployee).isPresent();
        assertThat(foundEmployee.get().getFirstName()).isEqualTo("John");
        assertThat(foundEmployee.get().getLastName()).isEqualTo("Engineering");
    }
}