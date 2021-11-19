package com.esliceu.Objects.services;

import org.springframework.ui.Model;

import java.util.Date;

public interface UserService {
    public boolean checkLogin(String username, String password);

    boolean signup(Model m, String username, String password, String firstName, String lastName, String birthDate, String email);
}
