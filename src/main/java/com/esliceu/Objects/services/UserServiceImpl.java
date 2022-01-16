package com.esliceu.Objects.services;

import com.esliceu.Objects.daos.UserDAO;
import com.esliceu.Objects.model.User;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    Utils utils;

    @Autowired
    HttpSession session;

    @Override
    public boolean checkLogin(String username, String password) {

        User u = userDAO.getUserByUsername(username);

        if (u == null) return false;

        return u.getPassword().equals(utils.getHash(password));
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
            if (password.length() < 7) {
                m.addAttribute("message", "La contrassenya ha de tenir com a mínim 8 caràcters");
                return false;
            }
            if (firstName.length() > 30) {
                m.addAttribute("message","El nom no pot tenir més de 30 caràcters");
                return false;
            }
            if (lastName.length() > 30) {
                m.addAttribute("message","El llinatge no pot tenir més de 30 caràcters");
                return false;
            }
            if (!birthDate.matches("^[0-3][0-9]-[0-1][0-9]-\\d{4}$")){
                m.addAttribute("message","El format de la data no és correcte");
                return false;
            }
            if (!email.matches("^\\w+@\\w+\\.\\w+$")) {
                m.addAttribute("message","El format del correu electrònic no és correcte");
                return false;
            }
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);

            User u = new User();
            u.setUsername(username);
            u.setPassword(utils.getHash(password));
            u.setName(firstName);
            u.setSurname(lastName);
            u.setBirthDate(date);
            u.setEmail(email);

            userDAO.save(u);


        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(Model m, String password, String firstName, String lastName, String birthDate, String email, String confirmPassowrd) {

        String username = (String) session.getAttribute("username");
        User u = userDAO.getUserByUsername(username);
        if (u.getPassword().equals(utils.getHash(confirmPassowrd))){
            if (!email.matches("^\\w+@\\w+\\.\\w+$")){
                m.addAttribute("message","El format del correu no és correcte");
                return false;
            }
            if (password.equals("")){
                password=u.getPassword();
            }else {
                password=utils.getHash(password);
            }
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            userDAO.updateUser(password,firstName,lastName,date,email);
            m.addAttribute("message","S'han realitzat els canvis");
            return true;
        }else {
            m.addAttribute("message","Contrassenya incorrecta");
        }


        return false;
    }

    @Override
    public User getUser(String username) {
        return userDAO.getUserByUsername(username);
    }


    @Override
    public boolean deleteUser(Model m, String username, String password) {
        if (userDAO.getUserByUsername(username).getPassword().equals(password)){
            userDAO.deleteUserByUsername(username);
            return true;
        }else {
            m.addAttribute("message","Contrassenya Incorrecta");
            return false;
        }
    }

}
