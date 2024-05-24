package com.employee.service;

import com.employee.entities.EmployeeDetails;
import com.employee.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDetails save(EmployeeDetails employeeDetails) {
        EmployeeDetails save = employeeRepository.save(employeeDetails);
        return save;

    }

    public List<EmployeeDetails> getAllEmployeeDetails() {
        return employeeRepository.findAll();
    }

    public EmployeeDetails getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

}
