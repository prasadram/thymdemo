package org.letuslearn.thymdemo.service;

import jakarta.annotation.PostConstruct;
import org.letuslearn.thymdemo.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private Map<String, Employee> employeeCache = new HashMap<>();
    @Override
    public Employee getEmployeeById(String id) {
        log.info("op=get_employee, status=OK, desc=fetching employee details by id:{}", id);
        return employeeCache.get(id);
    }

    @Override
    public Employee updateEmployee(String id, String name) {
        return employeeCache.put(id, new Employee(id, name));
    }

    @PostConstruct
    private void populateData() {
        employeeCache.put("123", new Employee("123", "John"));
        employeeCache.put("124", new Employee("124", "Bob"));
        employeeCache.put("125", new Employee("125", "Raj"));
    }
}
