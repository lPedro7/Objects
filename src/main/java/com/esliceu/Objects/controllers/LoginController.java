package com.esliceu.Objects.controllers;

import com.esliceu.Objects.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String login() {
        session.invalidate();
        return "login";
    }

    @PostMapping("/login")
    public RedirectView login(Model m, @RequestParam String username, @RequestParam String password) {
        if (userService.checkLogin(username, password)) {
            session.removeAttribute("message");
            session.setAttribute("username", username);
            return new RedirectView("/objects");
        } else {
            session.setAttribute("message", "Usuari o password incorrectes");
        }
        return new RedirectView("/login");

    }

    @GetMapping("/signup")
    public String signup() {
        session.invalidate();
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model m,
                         @RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String birthDate,
                         @RequestParam String email) {

        userService.signup(m, username, password, firstName, lastName, birthDate, email);
        return "login";

    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "login";
    }

}
