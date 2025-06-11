package com.employee.repository.mongo;

import com.employee.entity.mongo.Student;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StudentRepository extends MongoRepository<Student, String> {
}
