package com.employee.controller;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = "/save")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employeeRequest){
		log.info("save employee: {}", employeeRequest);
		return new ResponseEntity<>(this.employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
	}

	@PostMapping(path = "/saveAll")
	public ResponseEntity<List<Employee>> saveAllEmployee(@RequestBody List<Employee> employees){
		return new ResponseEntity<>(this.employeeService.saveAll(employees), HttpStatus.OK);
	}


	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Employee> getEmployee(@Valid @PathVariable(value = "id") Integer id){
		log.info("get employee with id: {}", id);
		return new ResponseEntity<>(this.employeeService.getEmployee(id), HttpStatus.OK);
	}

	@GetMapping(path = "/getAll")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return new ResponseEntity<>(this.employeeService.getAllEmployee(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@Valid @PathVariable(value = "id") Integer id){
		log.info("delete employee with id: {}", id);
		this.employeeService.deleteEmployee(id);
		return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
	}

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable(value = "id") Integer id, @RequestBody Employee employeeRequest){
		log.info("update employee with id: {}", id);
		return new ResponseEntity<>(this.employeeService.updateEmployee(id, employeeRequest), HttpStatus.OK);
	}
}
