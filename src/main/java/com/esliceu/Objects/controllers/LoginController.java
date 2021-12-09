package com.esliceu.Objects.controllers;

import com.esliceu.Objects.services.UserService;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/")
    public RedirectView start() {
        return new RedirectView("/login");
    }


    @GetMapping("/login")
    public String login() {
        session.invalidate();
        return "login";
    }

    @PostMapping("/login")
    public RedirectView login(HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        if (userService.checkLogin(username, password)) {
            session.removeAttribute("message");
            session.setAttribute("username", username);
            return new RedirectView("/objects");
        } else {
            response.setStatus(400);
            session.setAttribute("message", "Usuari o password incorrectes");
            return new RedirectView("/login");
        }

    }

    @GetMapping("/signup")
    public String signup() {
        session.invalidate();
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signup(Model m,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String birthDate,
                               @RequestParam String email,
                               HttpServletResponse response) {

        if (userService.getUser(username) != null) {
            response.setStatus(400);
            session.setAttribute("message", "Aquest usuari ja existeix");
            return new RedirectView("/signup");
        }

        userService.signup(m, Utils.unaccent(username), Utils.unaccent(password), Utils.unaccent(firstName), Utils.unaccent(lastName), birthDate, Utils.unaccent(email));
        if (userService.checkLogin(Utils.unaccent(username), Utils.unaccent(password))) {
            session.setAttribute("username", username);
            return new RedirectView("/objects");
        } else
            response.setStatus(400);
        session.setAttribute("message", "Error creant l'usuari");
        return new RedirectView("/signup");


    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "login";
    }

}
