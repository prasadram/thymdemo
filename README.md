# thymdemo
* This is a sample application to demo thymeleaf with springboot
* This is employee management application
* It will use in-memory db i.e hashmap
* During server startup it self it will load two employees into in-memory
* During update employee it will use ajax call to update employee details
* it has below end points
  * /localhost:8080/ which will give home page
  * /localhost:8080/employee?id=<employee id> this will give details of an employee
  * /localhost:8080/employee/{employeeId} it will update employee name
