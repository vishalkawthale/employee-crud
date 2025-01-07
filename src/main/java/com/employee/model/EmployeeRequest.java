package com.employee.model;

import com.employee.entity.Address;
import lombok.Builder;

@Builder
public class EmployeeRequest {

    private String id;
    private String firstName;
    private String lastName;
    private Address address;

}
