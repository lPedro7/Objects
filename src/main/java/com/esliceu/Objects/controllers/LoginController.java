package com.esliceu.Objects.controllers;

import com.esliceu.Objects.services.UserService;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public String login(HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        if (userService.checkLogin(username, password)) {
            session.removeAttribute("message");
            session.setAttribute("username", username);
            return "objects";
        } else {
            response.setStatus(400);
            session.setAttribute("message", "Usuari o password incorrectes");
            return "login";
        }

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
                         @RequestParam String email,
                         HttpServletResponse response) {

        userService.signup(m, Utils.unaccent(username), Utils.unaccent(password), Utils.unaccent(firstName), Utils.unaccent(lastName), birthDate, Utils.unaccent(email));
        if (userService.checkLogin(Utils.unaccent(username), Utils.unaccent(password))) {
            session.setAttribute("username", username);
            return "objects";
        } else
            response.setStatus(400);
        session.setAttribute("message", "Error creant l'usuari");
        return "signup";

    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "login";
    }

}
