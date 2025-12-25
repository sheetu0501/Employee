package com.Employee.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
