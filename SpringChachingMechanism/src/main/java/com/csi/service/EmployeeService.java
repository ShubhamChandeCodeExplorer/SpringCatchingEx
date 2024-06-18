package com.csi.service;

import com.csi.entity.Employee;
import com.csi.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepoImpl;

    public Employee signUp(Employee employee){
      return   employeeRepoImpl.save(employee);
    }

    public boolean signIn(String empEmailId,String empPassword){
        boolean flag=false;
        for(Employee employee:findAll()){
            if(employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
                flag=true;
            }
        }
        return flag;
    }
    @Cacheable(value = "empId")
    public Optional<Employee> findById(int empId){
        log.info("###########TRYING TO FETCH DATA FROM DB############");
        return employeeRepoImpl.findById(empId);
    }

    public List<Employee> findByEmpName(String empName){
        return employeeRepoImpl.findByEmpName(empName);
    }

    public Optional<Employee> findByEmpContact(long empContact){
        return employeeRepoImpl.findByEmpContact(empContact);
    }

    public Employee findByEmpEmailIdAndEmpPassword(String empEmailId,String empPassword){
        return employeeRepoImpl.findByEmpEmailIdAndEmpPassword(empEmailId,empPassword);
    }

    public List<Employee> findAll(){
        return employeeRepoImpl.findAll();
    }

    public Employee update(Employee employee){
        return employeeRepoImpl.save(employee);
    }

    public void deleteById(int empId){
         employeeRepoImpl.deleteById(empId);
    }

    public void deleteAll(){employeeRepoImpl.deleteAll();}
}
