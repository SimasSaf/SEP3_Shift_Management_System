package group3;
import group3.model.Employee;
import group3.model.Shift;
import group3.model.TransferObject;
import group3.model.User;
import group3.repository.IEmployeeRepository;
import group3.repository.IShiftRepository;
import group3.repository.IUserRepository;
import group3.util.BeanUtility;

import java.util.Arrays;

public class TestObject {

    TransferObject newObj = null;

    IUserRepository userRepository = BeanUtility.getApplicationContext().getBean(IUserRepository.class);
    IEmployeeRepository employeeRepository = BeanUtility.getApplicationContext().getBean(IEmployeeRepository.class);
    IShiftRepository shiftRepository = BeanUtility.getApplicationContext().getBean(IShiftRepository.class);

    public TransferObject TestObject(Object object) {


        new TransferObject(null, null);
        Object request = null;
        String command = null;


        if (object instanceof TransferObject) {


            request = ((TransferObject) object).getObject();
            command = ((TransferObject) object).getCommand();

            newObj = testUser(request, command);
            newObj = testShift(newObj.getObject(), newObj.getCommand());

            System.out.println(newObj);
        }
        return newObj;
    }


    private TransferObject testShift(Object request, String command) {

        if (request instanceof Shift shiftObj) {

            System.out.println("Command: " + command + " " + request.toString() + " "
                    + " " + ((Shift) request).getId() + " " + ((Shift) request).getEmployee_id());

            switch (command) {
                case "post" -> shiftRepository.save(shiftObj);
                case "all" -> {
                    System.out.println("Finding All...");
                    request = shiftRepository.findAll();
                }
                case "delete" -> {
                    shiftRepository.delete(shiftObj);
                }
                case "getShiftById" -> {
                    System.out.println("Getting user by id...");
                    request = shiftRepository.findShiftById(((Shift) request).getId());
                }

                case "EnrollToShift" -> {
                    Shift shift = shiftRepository.findShiftById(shiftObj.getId());
                    Employee employee = (Employee) employeeRepository.findEmployeeById(shiftObj.getEmployee_id());

                    System.out.println(employee.getId() + " " + employee.getHours() + "   SHIFT OBJ : " + shift.getDescription());

                    shift.enrollEmployee(employee);
                    shiftRepository.save(shift);
                    employeeRepository.save(employee);
                }
            }
            return new TransferObject(request, command);
        }
        return new TransferObject(request, command);
    }

    private TransferObject testUser(Object request, String command) {
        if (request instanceof User userObj) {
            switch (command) {

                case "all" -> {
                    System.out.println("Finding All...");
                    request = userRepository.findAll();
                }

                case "post" -> userRepository.save(userObj);

                case "update" -> {
                    userRepository.save(userObj);
                }

                case "delete" -> {
                    userRepository.delete(userObj);
                }

                case "getUserById" -> {
                    request = userRepository.findUserById(((User) request).getId());
                }

                case "allEmployees" -> {
                    request = employeeRepository.findAll();
                }
                case "getEmployeeById" -> {
                    request = employeeRepository.findEmployeeById(((Employee) request).getId());
                }
                case "deleteEmployee" -> {
                    employeeRepository.delete(new Employee(((Employee) request).getId()));
                }
                case "updateEmployee" -> {
                    employeeRepository.save((Employee)userObj);
                }

            }
            return new TransferObject(request, command);
        }
        return new TransferObject(request, command);
    }

}


