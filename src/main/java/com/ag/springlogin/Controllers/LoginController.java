package com.ag.springlogin.Controllers;

import com.ag.springlogin.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping(path = "/")
    public String WelcomePage() {
        return "redirect:login";
    }

    @GetMapping(path = "/login")
    public String Login() {
        return "login";
    }

    @GetMapping(path = "/login/signout")
    public String logout(HttpServletRequest request, Model model) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping(path = "/login/firstacces")
    public String firstAccess(HttpServletRequest request, Model model) {
        return "firstAccess";
    }

    @ModelAttribute("title")
    public String title() {
        return "Log In";
    }

    @ModelAttribute("home")
    public String home() {
        return "login";
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }
}
