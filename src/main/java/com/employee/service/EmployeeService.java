package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	public Employee getEmployee(Integer id) {
		return this.employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Employee object not found for id = %s", id)));
		//log.error("Some error");
	}

	public List<Employee> saveAll(List<Employee> employees){
		return this.employeeRepository.saveAll(employees);
	}

}
