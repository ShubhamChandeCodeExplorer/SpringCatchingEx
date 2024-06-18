package com.csi.repo;

import com.csi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    //Custome Method
    public List<Employee> findByEmpName(String empName);

    public Employee findByEmpEmailIdAndEmpPassword(String empEmailId,String empPassword);

    public Optional<Employee> findByEmpContact(long empContact);
}
