package com.employee.controller;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
@Slf4j
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(path = "/save")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employeeRequest){
		log.info("save employee: {}", Thread.currentThread());
		return new ResponseEntity<>(this.employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") Integer id){
		log.info("get employee: {}", Thread.currentThread());
		return new ResponseEntity<>(this.employeeService.getEmployee(id), HttpStatus.OK);
	}

	@PatchMapping(path = "/saveAll")
	public ResponseEntity<List<Employee>> saveAllEmployee(@RequestBody List<Employee> employees){
		return new ResponseEntity<>(this.employeeService.saveAll(employees), HttpStatus.OK);
	}
}
