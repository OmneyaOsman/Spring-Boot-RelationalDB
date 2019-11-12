package com.omni.demo.repository;

import com.omni.demo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    int insertEmployee(Employee employee);
    void insertEmployees(List<Object[]> employees);
    int updateEmployeeFirstName(Employee employee);
    int deleteById(Long id);
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    Optional<Employee> findByFirstName(String firstName);
    int count();
    void createTable();
    void dropTableIfExists();

}
