package group3.validation;

import group3.InitializeConnection;
import group3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ValidateUserImpl implements IValidateUser {

    @Autowired
    InitializeConnection initializeConnection;

    @Override
    public boolean userExists(User user) throws IOException, ClassNotFoundException {

        List<User> allUsers = (List<User>) initializeConnection.sendTransferObject("all", user);

        System.out.println("Received all employees");
        //checks if password and username match in the dbs
        boolean match = allUsers.stream().anyMatch(o -> o.getUsername().equals(user.getUsername()) && o.getPassword().equals(user.getPassword()));

        System.out.println("Does match? " + match);
        if (match) {
            System.out.println("Does contain login and password returning true...");
            return true;
        } else {
            System.out.println("Does not contain, returning false");
            return false;
        }
    }

    @Override
    public String userAuthState(User user) throws IOException, ClassNotFoundException {

        List<User> users = (List<User>) initializeConnection.sendTransferObject("all", user);

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUsername().equals(user.getUsername()))
            {
                return users.get(i).getAuthLevel();
            }
        }
        return "Did not pass the for loop, no account found";
    }

    @Override
    public User userInfo(User user) throws IOException, ClassNotFoundException {

        System.out.println(user.getUsername() + " " + user.getFirstName() + " " + user.getPassword() );

        List<User> users = (List<User>) initializeConnection.sendTransferObject("all", user);

        for (User value : users) {

            if (value.getUsername().equalsIgnoreCase(user.getUsername()) && value.getPassword().equals(user.getPassword())) {
                System.out.println("Sending back " + value.getAuthLevel());
                return value;
            }
        }
        return null;
    }
}
