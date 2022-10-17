package group3.controllers;

import group3.InitializeConnection;
import group3.model.User;
import group3.validation.IValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersControllerImpl implements IUsersController {

    @Autowired
    InitializeConnection initializeConnection;

    @Autowired
    IValidateUser validateUser;


    @GetMapping("/all")
    public List<User> getAllUsers() throws IOException, ClassNotFoundException {
        System.out.println("Getting All");
        User user = new User();
        List<User> allUsers = (List<User>) initializeConnection.sendTransferObject("all", user);
        return allUsers;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long userId) throws IOException, ClassNotFoundException {
        User user = new User();
        user = (User) initializeConnection.sendTransferObject("getUserById", new User(userId));
        return user;

    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) throws IOException, ClassNotFoundException {

        System.out.println("Posting...");
        User newUser = (User) initializeConnection.sendTransferObject("post", user);
        System.out.println(newUser.getUsername());
        return newUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long userId, @RequestBody User user) throws IOException, ClassNotFoundException {

        System.out.println("Updating...");
        User newUser = (User) initializeConnection.sendTransferObject("update", user);
        return newUser;
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") Long userId) throws IOException, ClassNotFoundException {
        System.out.println("Deleting...");
        User user = (User) initializeConnection.sendTransferObject("delete", new User(userId));
        return user;
    }

    public boolean checkIfUserExists(@RequestBody User user) throws IOException, ClassNotFoundException
    {
        if (validateUser.userExists(user))
        {
            System.out.println("Returning true from API");
            return true;
        }
        else
        {
            System.out.println("Returning false from API");
            return false;
        }
    }

    public String checkUserAuthState(@RequestBody User user) throws IOException, ClassNotFoundException
    {
        return validateUser.userAuthState(user);
    }

    @PostMapping("validate")
    public User getUser(@RequestBody User user) throws IOException, ClassNotFoundException
    {
        return validateUser.userInfo(user);
    }
}
