package group3.controllers;


import group3.model.User;

import java.io.IOException;
import java.util.List;

public interface IUsersController
{

    public List<User> getAllUsers() throws IOException, ClassNotFoundException;
    public User getUserById(Long userId) throws ClassNotFoundException, IOException;
    public User createUser(User user) throws IOException, InterruptedException, ClassNotFoundException;
    public User updateUser(Long userId, User user) throws IOException, ClassNotFoundException;
    public User deleteUser(Long userId) throws IOException, ClassNotFoundException;
    public String checkUserAuthState(User user) throws IOException, ClassNotFoundException;
    public boolean checkIfUserExists(User user) throws IOException, ClassNotFoundException;
}
