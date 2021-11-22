package com.esliceu.Objects.services;

import com.esliceu.Objects.daos.UserDAO;
import com.esliceu.Objects.model.User;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    Utils utils;

    @Override
    public boolean checkLogin(String username, String password) {

        User u = userDAO.getUser(username);

        if (u == null) return false;

        return u.getPassword().equals(password);
    }

    @Override
    public boolean signup(Model m, String username,
                          String password,
                          String firstName,
                          String lastName,
                          String birthDate,
                          String email) {
        try {

            if (username.length() < 6 || username.length() > 20) {
                m.addAttribute("message", "L'usuari ha de tenir entre 6 i 20 caràcters");
                return false;
            }
            if (password.length() < 7 || password.length() > 25) {
                m.addAttribute("message", "La contrassenya ha de tenir com a mínim 8 caràcters");
                return false;
            }
            if (firstName.length() > 30) {
                m.addAttribute("El nom no pot tenir més de 30 caràcters");
                return false;
            }
            if (lastName.length() > 30) {
                m.addAttribute("El llinatge no pot tenir més de 30 caràcters");
                return false;
            }
            if (email.length() > 80) {
                m.addAttribute("Correu electrònic no pot tenir més de 80 caràcters");
                return false;
            }

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);

            if (userDAO.getUser(username) == null) {
                userDAO.createUser(username, password, firstName, lastName, date, email);
            }else {
                m.addAttribute("message","Aquest nom d'usuari ja existeix");
            }
        } catch (ParseException e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

    @Override
    public void updateUser(String password, String firstName, String lastName, String birthDate, String email, String confirmPassowrd) {

    }

    @Override
    public User getUser(String username) {
        return userDAO.getUser(username);
    }


    @Override
    public void deleteUser(Model m, String username, String password) {
        if (userDAO.getUser(username).getPassword().equals(password)){
            userDAO.deleteUser(username);
        }else {
            m.addAttribute("message","Contrassenya incorrecta");
        }
    }
}
