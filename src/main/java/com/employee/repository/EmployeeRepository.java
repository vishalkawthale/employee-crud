package com.employee.repository;

import com.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /*@Query(value = "SELECT e FROM Employee e WHERE e.lastName = ?1")
    Page<Employee> findByLastName(String lastName);
    @Query(value = "SELECT e FROM Employee e WHERE e.firstName = ?1", nativeQuery = true)
    List<Employee> findByFirstName(String firstName);*/

}
