package com.example.service;

import com.example.entities.Employee;


import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void deleteServiceById(int employeeID);

    public Optional<Employee> getServiceById(int employeeID);

    public List<Employee> getAllEmployeesByProviderID(int providerID);

    public long countAllEmployeesByProviderID(int providerID);

    public long countEmployees();

    public void deleteEmployeeById(int employeeID);

    public Optional<Employee> getEmployeeByID(int employeeID);
}
