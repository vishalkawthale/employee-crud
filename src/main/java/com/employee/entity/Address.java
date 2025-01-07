package com.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String addressId;
    private String street;
    private String city;
    private String state;
}
