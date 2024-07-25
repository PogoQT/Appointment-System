package com.example.serviceImpl;

import com.example.entities.Employee;
import com.example.entities.Provider;
import com.example.repository.EmployeeRepository;
import com.example.repository.ProviderRepository;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public void deleteServiceById(int employeeID) {
        employeeRepo.deleteById(employeeID);
    }

    @Override
    public Optional<Employee> getServiceById(int employeeID) {
        return employeeRepo.findById(employeeID);
    }

    @Override
    public List<Employee> getAllEmployeesByProviderID(int providerID) {
        return employeeRepo.findAllByProviderID(providerID);
    }

    @Override
    public long countAllEmployeesByProviderID(int providerID) {
        return employeeRepo.countByProviderID(providerID);
    }



    @Override
    public long countEmployees() {
        return employeeRepo.count();
    }

    @Override
    public void deleteEmployeeById(int employeeID) {
        employeeRepo.deleteById(employeeID);
    }

    @Override
    public Optional<Employee> getEmployeeByID(int employeeID) {
        return employeeRepo.findById(employeeID);
    }
}
