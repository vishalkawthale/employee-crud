package com.employee.service;

import com.employee.entity.mongo.Student;
import com.employee.repository.mongo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Student getStudent(String id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(String id) {
        this.studentRepository.deleteById(id);
    }

    public void updateStudent(String id, Student student) {
        Student studentToUpdate = this.studentRepository.findById(id).orElse(null);
        if (studentToUpdate != null) {
            studentToUpdate.setName(student.getName());
            studentToUpdate.setEmail(student.getEmail());
            studentToUpdate.setPhone(student.getPhone());
            this.studentRepository.save(studentToUpdate);
        }
    }

    public void deleteAllStudents() {
        this.studentRepository.deleteAll();
    }

    public void saveAllStudents(Iterable<Student> students) {
        this.studentRepository.saveAll(students);
    }

    public Iterable<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }
}
