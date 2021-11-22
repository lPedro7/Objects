package com.esliceu.Objects.controllers;

import com.esliceu.Objects.services.UserService;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @Autowired
    Utils utils;

    @GetMapping("/private/settings")
    public String main() {
        session.setAttribute("user", userService.getUser((String) session.getAttribute("username")));
        session.setAttribute("birthDate",utils.DateToString(userService.getUser((String) session.getAttribute("username")).getBirthDate()));
        return "settings";
    }

    @PostMapping("/private/settings")
    public String main(@RequestParam String password,
                       @RequestParam String firstName,
                       @RequestParam String lastName,
                       @RequestParam String birthDate,
                       @RequestParam String email,
                       @RequestParam String confirmPassowrd) {

        userService.updateUser(password, firstName, lastName, birthDate, email, confirmPassowrd);

        return "settings";
    }

    @PostMapping("/private/deleteUser")
    public String deleteUser(Model m, @RequestParam String password){
        userService.deleteUser(m,(String) session.getAttribute("username"),password);
        return "login";
    }

}
