package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.User;

import java.util.Date;
import java.util.List;

public interface UserDAO {
    public List<User> getUsers();
    public User getUser(String username);

    void createUser(String username, String password, String firstName, String lastName, Date birthDate, String email);
}
