package group3.controllers;

import group3.InitializeConnection;
import group3.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeControllerImpl implements IEmployeeController {

    @Autowired
    InitializeConnection initializeConnection;


    //save employee
    @PostMapping("add")
    public Employee createEmployee(@RequestBody Employee employee) throws IOException, ClassNotFoundException { //Request Body Deserializes

        System.out.println("Posting...");
        Employee newEmployee = (Employee) initializeConnection.sendTransferObject("post", employee); //should check this in another place maybe instead
        System.out.println(newEmployee.getUsername());
        return newEmployee;
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() throws IOException, ClassNotFoundException {
        System.out.println("Getting All employees");
        Employee employee = new Employee();
        return (List<Employee>) initializeConnection.sendTransferObject("allEmployees", employee);
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long employeeId) throws IOException, ClassNotFoundException {
        Employee employee = new Employee();
        employee = (Employee) initializeConnection.sendTransferObject("getEmployeeById", new Employee(employeeId));
        return employee;
    }

    @DeleteMapping("/delete/{id}")
    public Employee deleteEmployee(@PathVariable("id") Long employeeId) throws IOException, ClassNotFoundException {
        System.out.println("Deleting...");
        return (Employee) initializeConnection.sendTransferObject("deleteEmployee", new Employee(employeeId));
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employee) throws IOException, ClassNotFoundException {

        System.out.println("Updating...");
        Employee newEmployee = (Employee) initializeConnection.sendTransferObject("updateEmployee", employee);
        return newEmployee;
    }

}
