package com.employee.controller;

import com.employee.entities.EmployeeDetails;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public String saveEmployee(@RequestBody EmployeeDetails employeeDetails) {
        EmployeeDetails savedEmployee = employeeService.save(employeeDetails);
        return "Employee saved successfully";
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDetails>> getEmployees() {
        List<EmployeeDetails> employeeDetails = employeeService.getAllEmployeeDetails();
        return new ResponseEntity<>(employeeDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getEmployeeById(@PathVariable Long id) {
        EmployeeDetails employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return Collections.singletonMap("error", "Employee not found");
        }
        return calculateTaxDetails(employee);
    }

    private Map<String, Object> calculateTaxDetails(EmployeeDetails employee) {
        Map<String, Object> result = new HashMap<>();
        result.put("employeeCode", employee.getEmployeeId());
        result.put("firstName", employee.getFirstName());
        result.put("lastName", employee.getLastName());

        double yearlySalary = calculateEffectiveSalary(employee);
        double taxAmount = calculateTax(yearlySalary);
        double cessAmount = calculateCess(yearlySalary);

        result.put("yearlySalary", yearlySalary);
        result.put("taxAmount", taxAmount);
        result.put("cessAmount", cessAmount);

        return result;
    }

    private double calculateEffectiveSalary(EmployeeDetails employee) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(employee.getDateOfJoin());
        int startMonth = startCalendar.get(Calendar.MONTH);
        int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);

        int monthsWorked = 12 - startMonth;
        if (startDay > 1) {
            monthsWorked -= 1;
        }

        double dailySalary = employee.getYearlySalary() / 365;
        double salaryForPartialMonth = (30 - startDay + 1) * dailySalary;
        return (monthsWorked * employee.getYearlySalary() / 12) + salaryForPartialMonth;
    }

    private double calculateTax(double salary) {
        double tax = 0;
        if (salary <= 250000) {
            return tax;
        } else if (salary <= 500000) {
            tax = (salary - 250000) * 0.05;
        } else if (salary <= 1000000) {
            tax = 250000 * 0.05 + (salary - 500000) * 0.1;
        } else {
            tax = 250000 * 0.05 + 500000 * 0.1 + (salary - 1000000) * 0.2;
        }
        return tax;
    }

    private double calculateCess(double salary) {
        if (salary > 2500000) {
            return (salary - 2500000) * 0.02;
        }
        return 0;
    }
}