package pl.wszib.edu.duda.database;

import pl.wszib.edu.duda.model.User;

import java.util.List;

public interface IUserRepository {
    User findUserByLogin(String login);
    List<User> getUsers();
}
