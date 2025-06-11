package com.employee.repository.sql.mysql;

import com.employee.entity.sql.mysql.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT e FROM Employee e WHERE e.lastName = :lastName")
    Page<Employee> findByLastName(String lastName);
    @Query(value = "SELECT e FROM Employee e WHERE e.firstName = ?1", nativeQuery = true)
    List<Employee> findByFirstName(String firstName);

    @Procedure(name = "GET_EMPLOYEE_BY_NAME")
    Employee getEmployeeByName(String firstName, String lastName);

    @Procedure(name = "GET_EMPLOYEE_BY_ID")
    Employee getEmployeeById(Integer id);


}
