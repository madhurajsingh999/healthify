package com.healthify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthify.repository.EmployeeDetailsRepository;

import entity.EmployeeDetails;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDetailsService {

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    public List<EmployeeDetails> getAllEmployees() {
        return employeeDetailsRepository.findAll();
    }

    public Optional<EmployeeDetails> getEmployeeById(int id) {
        return employeeDetailsRepository.findById(id);
    }

    public EmployeeDetails createEmployee(EmployeeDetails employeeDetails) {
        return employeeDetailsRepository.save(employeeDetails);
    }

    public EmployeeDetails updateEmployee(int id, EmployeeDetails employeeDetails) {
        if (employeeDetailsRepository.existsById(id)) {
            employeeDetails.setId(id); // Ensure the ID remains consistent
            return employeeDetailsRepository.save(employeeDetails);
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }

    public void deleteEmployee(int id) {
        if (employeeDetailsRepository.existsById(id)) {
            employeeDetailsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }
}
