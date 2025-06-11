package com.employee.entity.sql.mysql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;


@Entity(name = "employee")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 *
 * CREATE PROCEDURE GET_EMPLOYEE_BY_NAME(IN first_name_in VARCHAR(50), IN last_name_in VARCHAR(50), OUT employee_out employee)
 * BEGIN
 *    SELECT * into employee_out from employee WHERE first_name = first_name_in AND last_name = last_name_in;
 *    IF employee_out IS NULL THEN
 *    SIGNAL SQLSTATE '45000'
 *    SET MESSAGE_TEXT = 'Employee not found';
 *    END IF;
 * END
 *
 *
 */
/*@NamedQueries({
		@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM employee e"),
		@NamedQuery(name = "Employee.findById", query = "SELECT e FROM employee e WHERE e.id = :id"),
		@NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM employee e WHERE e.firstName = :firstName"),
		@NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM employee e WHERE e.lastName = :lastName"),
		@NamedQuery(name = "Employee.findByCreditCardNumber", query = "SELECT e FROM employee e WHERE e.creditCardNumber = :creditCardNumber")
})
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(
				name = "GET_EMPLOYEE_BY_ID",
				procedureName = "GET_EMPLOYEE_BY_ID",
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "id_in", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "employee_out", type = Employee.class)
				}
		),
		@NamedStoredProcedureQuery(
				name = "GET_EMPLOYEE_BY_CREDIT_CARD_NUMBER",
				procedureName = "GET_EMPLOYEE_BY_CREDIT_CARD_NUMBER",
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "credit_card_number_in", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "employee_out", type = Employee.class)
				}
		),
		@NamedStoredProcedureQuery(
				name = "GET_EMPLOYEE_BY_NAME",
				procedureName = "GET_EMPLOYEE_BY_NAME",
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "first_name_in", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "last_name_in", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "employee_out", type = Employee.class)
				}

		)

})*/
public class Employee {
	@Id
	@Builder.Default
	@NotNull(message = "id must not be empty")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = 1;
	@NotBlank(message = "firstName must not be empty")
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "addressId")
	private Address address;
	private Double salary;
}
