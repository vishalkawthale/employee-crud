package com.employee.service;

import com.employee.entity.sql.mysql.Employee;
import com.employee.repository.sql.mysql.EmployeeRepository;
import com.employee.utils.LogExecutionTime;
import com.employee.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
@Slf4j
public class EmployeeService {

    @Autowired
	private EmployeeRepository employeeRepository;

	@LogExecutionTime
	public Employee saveEmployee(Employee employee) {
		employee.setCreatedAt(Timestamp.from(Instant.now()));
		return this.employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployee() {
		return this.employeeRepository.findAll();
	}

	public List<Employee> getAllEmployeeByIds(List<Integer> ids) {
		return this.employeeRepository.findAllById(ids);
	}

	public void deleteEmployee(Integer id) {
		this.employeeRepository.deleteById(id);
	}

	public Employee updateEmployee(Integer id, Employee employee) {
		Employee employeeToUpdate = this.employeeRepository.findById(id)
				.orElseThrow(() -> {
					log.error("Employee not found for id = {}", id);
					return new NotFoundException(String.format("Employee object not found for id = %s", id));
				});
		employeeToUpdate.setFirstName(employee.getFirstName());
		employeeToUpdate.setLastName(employee.getLastName());
		employeeToUpdate.setAddress(employee.getAddress());
		employeeToUpdate.setUpdatedAt(Timestamp.from(Instant.now()));
		return this.employeeRepository.save(employeeToUpdate);
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

	/*public List<Employee> findByFirstName(String firstName){
		return this.employeeRepository.findByFirstName(firstName);
	}

	public List<Employee> findByLastName(String lastName){
		return this.employeeRepository.findByLastName(lastName).getContent();
	}*/

}
