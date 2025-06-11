package com.employee.entity.sql.postgres;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity(name = "users")
@Builder
@Data
public class User{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Integer id;
        String firstName;
        String lastName;
        String email;
        @Column(unique = true, nullable = false, length = 50, name = "username",
        updatable = false, columnDefinition = "VARCHAR(50)")
        String username;
        String password;
        String phone;
        List<String> role;
        String status;
        Timestamp createdAt;
        Timestamp updatedAt;


}
