package com.employee.controller;

import com.employee.entity.mongo.Student;
import com.employee.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/student")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @PostMapping(path = "/save")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student){
        log.info("save employee: {}", student);
        return new ResponseEntity<>(this.studentService.saveStudent(student), HttpStatus.CREATED);
    }

    @PostMapping(path = "/saveAll")
    public ResponseEntity<Void> saveAllStudents(@RequestBody Iterable<Student> students){
        log.info("save all students");
        this.studentService.saveAllStudents(students);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<Void> deleteAllStudents(){
        log.info("delete all students");
        this.studentService.deleteAllStudents();
        someMethod();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void someMethod() {
        // Some logic here
        log.info("someMethod executed");
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateStudent(@Valid @RequestBody Student student){
        log.info("update student: {}", student);
        this.studentService.updateStudent(student.getId(), student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@Valid @PathVariable String id){
        log.info("delete student with id: {}", id);
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<Iterable<Student>> getAllStudents(){
        log.info("get all students");
        return new ResponseEntity<>(this.studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Student> getStudent(@Valid @PathVariable String id){
        log.info("get student with id: {}", id);
        return new ResponseEntity<>(this.studentService.getStudent(id), HttpStatus.OK);
    }


}
