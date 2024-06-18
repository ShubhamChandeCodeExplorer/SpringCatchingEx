package com.csi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int empId;
    @Size(min = 2,message = "Employee name Should have Atleast 2 Chracter.")
    private String empName;
    private String empAddress;
    @Range(min = 1000000000,max = 9999999999l,message = "Contact have only 10 digit")
    @Column(unique = true)
    private long empContact;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;
    private double empSalary;
    @Email(message = "Email should be valid.")
    private String empEmailId;
    @Size(min = 4,message = "Password Have 2 Character.")
    private String empPassword;
}
