package group3.validation;

import group3.model.User;

import java.io.IOException;

public interface IValidateUser
{
    public boolean userExists(User user) throws IOException, ClassNotFoundException;
    public String userAuthState(User user) throws IOException, ClassNotFoundException;
    public User userInfo(User user) throws IOException, ClassNotFoundException;
}
