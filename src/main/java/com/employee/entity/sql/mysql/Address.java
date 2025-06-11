package com.employee.entity.sql.mysql;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String addressId;
    private String street;
    private String city;
    private String state;
}
