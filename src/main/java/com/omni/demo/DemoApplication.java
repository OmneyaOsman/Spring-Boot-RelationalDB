package com.omni.demo;

import com.omni.demo.model.Employee;
import com.omni.demo.repository.EmployeeRepository;
import com.omni.demo.utils.SqlStatements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        runJDBC();
    }

    private void runJDBC() {
        log.info("Creating tables");
        employeeRepository.dropTableIfExists();
        employeeRepository.createTable();

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Joy Long")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting employee record for %s %s", name[0], name[1])));
        employeeRepository.insertEmployees(splitUpNames);

        log.info("Querying for employee records 1");
        employeeRepository.findAll().forEach(emp -> log.info(emp.toString()));

        log.info("Querying for employee records where first_name = 'Josh':");
        log.info(employeeRepository.findByFirstName("Josh").toString());

        log.info("Updating John employee record ");
        Employee employee = employeeRepository.findByFirstName("John").orElseThrow(IllegalArgumentException::new);
        employee.setFirstName("Ramy");
        employeeRepository.updateEmployeeFirstName(employee);
        log.info("Querying for employee records 2");
        employeeRepository.findAll().forEach(emp -> log.info(emp.toString()));
        log.info("Deleting  Jeff employee record ");
        employeeRepository.deleteById(employeeRepository.findByFirstName("Jeff").get().getId());
        log.info("Querying for employee records 3 ");
        employeeRepository.findAll().forEach(emp -> log.info(emp.toString()));
    }
}



