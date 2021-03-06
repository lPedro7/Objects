package com.esliceu.Objects.services;

import com.esliceu.Objects.model.User;
import org.springframework.ui.Model;

public interface UserService {
    boolean checkLogin(String username, String password);

    boolean signup(Model m, String username, String password, String firstName, String lastName, String birthDate, String email);

    boolean updateUser(Model m, String password, String firstName, String lastName, String birthDate, String email, String confirmPassowrd);

    User getUser(String username);

    boolean deleteUser(Model m, String username, String password);

}
