package com.healthify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthify.entity.EmployeeDetails;
import com.healthify.reponse.UserDefinedResponse;
import com.healthify.services.EmployeeDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @GetMapping
    public ResponseEntity<UserDefinedResponse<List<EmployeeDetails>>> getAllEmployees() {
        List<EmployeeDetails> employees = employeeDetailsService.getAllEmployees();
        return ResponseEntity.ok(UserDefinedResponse.success("Employee list fetched successfully", employees));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDefinedResponse<EmployeeDetails>> getEmployeeById(@PathVariable int id) {
        return employeeDetailsService.getEmployeeById(id)
                .map(employee -> ResponseEntity
                        .ok(UserDefinedResponse.success("Employee fetched successfully", employee)))
                .orElse(ResponseEntity.status(404).body(UserDefinedResponse.failure("Employee not found", null)));
    }

    @PostMapping
    public ResponseEntity<UserDefinedResponse<EmployeeDetails>> createEmployee(
            @RequestBody EmployeeDetails employeeDetails) {
        EmployeeDetails createdEmployee = employeeDetailsService.createEmployee(employeeDetails);
        return ResponseEntity.ok(UserDefinedResponse.success("Employee created successfully", createdEmployee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDefinedResponse<EmployeeDetails>> updateEmployee(@PathVariable int id,
            @RequestBody EmployeeDetails employeeDetails) {
        try {
            EmployeeDetails updatedEmployee = employeeDetailsService.updateEmployee(id, employeeDetails);
            return ResponseEntity.ok(UserDefinedResponse.success("Employee updated successfully", updatedEmployee));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(UserDefinedResponse.failure(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDefinedResponse<Void>> deleteEmployee(@PathVariable int id) {
        try {
            employeeDetailsService.deleteEmployee(id);
            return ResponseEntity.ok(UserDefinedResponse.success("Employee deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(UserDefinedResponse.failure(e.getMessage(), null));
        }
    }
}
