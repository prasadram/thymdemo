package org.letuslearn.thymdemo.controller;

import org.letuslearn.thymdemo.dto.Employee;
import org.letuslearn.thymdemo.dto.UpdateEmployeeCommand;
import org.letuslearn.thymdemo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;


    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String employeeId,
                                                   @RequestBody UpdateEmployeeCommand updateEmployeeCommand) {
        log.info("op=update_employee, status=OK, desc=employee id:{}", employeeId);
        Employee employee1 = employeeService.updateEmployee(employeeId, updateEmployeeCommand.name());
        if (null == employee1) {
            log.error("op=update_employee, status=KO, desc=employee not found:{}", employeeId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            log.info("op=update_employee, status=OK, desc=employee id:{} update with name:{}",
                    employeeId, updateEmployeeCommand.name());
            return ResponseEntity.ok(new Employee(employeeId, updateEmployeeCommand.name()));
        }
    }

    @GetMapping("/")
    public String getWelcomePage(Model model) {
        model.addAttribute("id", "");
        return "index";
    }

    @GetMapping("/employee")
    public String getEmployeePage(@RequestParam("id") String id,  Model model) {
        log.info("op=get_employee, status=OK, desc=employee id:{}", id);
        model.addAttribute("id", id);
        Employee employee = employeeService.getEmployeeById(id);
        if ( null != employee) {
            model.addAttribute("employee", employee);
        }
        return "employee";
    }
}
