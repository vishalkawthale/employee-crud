package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class EmployeeService {

    @Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	public Employee getEmployee(Integer id) {
		return this.employeeRepository.findById(id)
				.orElseThrow(() -> {
					log.error("Employee not found for id = {}", id);
					return new NotFoundException(String.format("Employee object not found for id = %s", id));
				});
		//log.error("Some error");
	}

	public List<Employee> saveAll(List<Employee> employees){
		return this.employeeRepository.saveAll(employees);
	}

}
