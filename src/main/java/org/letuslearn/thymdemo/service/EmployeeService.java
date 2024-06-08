package org.letuslearn.thymdemo.service;

import org.letuslearn.thymdemo.dto.Employee;

public interface EmployeeService {

     Employee getEmployeeById(String id);

     Employee updateEmployee(String id, String name);

}
