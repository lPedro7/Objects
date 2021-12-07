package com.esliceu.Objects.controllers;

import com.esliceu.Objects.services.UserService;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @Autowired
    Utils utils;

    @GetMapping("/settings")
    public String main() {
        session.setAttribute("message","");
        session.setAttribute("user", userService.getUser((String) session.getAttribute("username")));
        session.setAttribute("birthDate", utils.DateToString(userService.getUser((String) session.getAttribute("username")).getBirthDate()));
        return "settings";
    }

    @PostMapping("/settings")
    public String main(@RequestParam String password,
                       @RequestParam String firstName,
                       @RequestParam String lastName,
                       @RequestParam String birthDate,
                       @RequestParam String email,
                       @RequestParam String confirmPassword,
                       HttpServletResponse response) {

        String pattern = "^\\d{2}-\\d{2}-\\d{4}$";
        if (!birthDate.matches(pattern)) {
            response.setStatus(400);
            session.setAttribute("message","La data no és correcta");
            return "settings";
        }
        userService.updateUser(Utils.unaccent(password), Utils.unaccent(firstName), Utils.unaccent(lastName), birthDate, Utils.unaccent(email), Utils.unaccent(confirmPassword));
        response.setStatus(200);
        return "settings";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Model m, @RequestParam String password) {
        userService.deleteUser(m, (String) session.getAttribute("username"), utils.getHash(password));
        return "login";
    }

}
