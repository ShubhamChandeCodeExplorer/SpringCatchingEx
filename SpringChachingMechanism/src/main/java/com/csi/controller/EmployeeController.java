package com.csi.controller;

import com.csi.entity.Employee;
import com.csi.exception.RecordNotFoundException;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {
@Autowired
    private EmployeeService employeeServiceImpl;
    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServiceImpl.signUp(employee), HttpStatus.CREATED);
    }
    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId,String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId,empPassword));
    }
    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findByEmpId(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @GetMapping("/findbyname/{empName}")
    public ResponseEntity<List<Employee>> findByEmpName(@PathVariable String empName){
        return ResponseEntity.ok(employeeServiceImpl.findByEmpName(empName));
    }

    @GetMapping("/findbyempcontact/{empContact}")
    public ResponseEntity<Optional<Employee>> findByEmpContact(@PathVariable long empContact){
        return ResponseEntity.ok(employeeServiceImpl.findByEmpContact(empContact));
    }

    @GetMapping("/findbyemailandpassword/{empEmailId}/{empPassword}")
    public ResponseEntity<Employee> findByEmpEmailIdAndEmpPassword(@PathVariable String empEmailId,@PathVariable String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.findByEmpEmailIdAndEmpPassword(empEmailId,empPassword));
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee,@PathVariable int empId){
        Employee employee1=employeeServiceImpl.findById(empId).orElseThrow(()->new RecordNotFoundException("Employee Id Doesnt exists."));
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContact(employee.getEmpContact());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return   new ResponseEntity<>(employeeServiceImpl.update(employee1),HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Sucessfully....");
    }
    
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        employeeServiceImpl.deleteAll();
        return ResponseEntity.ok("All Data Deleted Succefully");
    }

    @GetMapping("/findbyanyinput/{input}")
    public ResponseEntity<List<Employee>> findByAnyInput(@PathVariable String anyInput){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream()
                .filter(emp -> dateFormat.format(emp.getEmpDOB()).equals(anyInput)
                        || String.valueOf(emp.getEmpId()).equals(anyInput) || emp.getEmpName().equals(anyInput)
                        || emp.getEmpEmailId().equals(anyInput)
                        || String.valueOf(emp.getEmpContact()).equals(anyInput)).collect(Collectors.toList()));
    }

}
