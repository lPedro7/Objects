package com.esliceu.Objects.daos;
import com.esliceu.Objects.model.User;

import java.util.Date;
import java.util.List;

public interface UserDAO {
    List<User> getUsers();

    User getUser(String username);

    void createUser(String username, String password, String firstName, String lastName, Date birthDate, String email);

    void deleteUser(String username);

}
