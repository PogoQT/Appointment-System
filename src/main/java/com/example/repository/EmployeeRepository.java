package com.example.repository;

import com.example.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e from Employee e where e.providerID = :providerID")
    List<Employee> findAllByProviderID(int providerID);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.providerID = :providerID")
    long countByProviderID(int providerID);
}
