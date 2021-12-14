package com.esliceu.Objects.controllers;

import com.esliceu.Objects.services.UserService;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        return "login";
    }

    @PostMapping("/login")
    public String login(Model m, HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        if (userService.checkLogin(username, password)) {
            session.removeAttribute("message");
            session.setAttribute("username", username);
            try {
                response.sendRedirect("/objects");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "/objects";
        } else {
            response.setStatus(400);
            m.addAttribute("message", "Usuari o password incorrectes");
            return "/login";
        }

    }

    @GetMapping("/signup")
    public String signup() {
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

        if (userService.getUser(username) != null) {
            response.setStatus(400);
            m.addAttribute("message","Aquest usuari ja existeix");
            return "/signup";
        }
        if (userService.signup(m, Utils.unaccent(username), Utils.unaccent(password), Utils.unaccent(firstName), Utils.unaccent(lastName), birthDate, Utils.unaccent(email))) {
            return "/login";
        }
        return "/signup";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "login";
    }

}
