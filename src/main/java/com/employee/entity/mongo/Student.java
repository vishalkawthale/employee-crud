package com.employee.entity.mongo;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "student")
@Data
public class Student {
    @Id
    @NotNull(message = "id must not be empty")
    private String id;
    @Field(name = "name")
    @NotBlank(message = "name must not be empty")
    private String name;
    @Field(name = "email")
    @Email(message = "Invalid email", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
    @Field(name = "phone")
    private String phone;
}
