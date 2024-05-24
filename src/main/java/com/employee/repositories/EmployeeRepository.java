package com.employee.repositories;

import com.employee.entities.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {
}
