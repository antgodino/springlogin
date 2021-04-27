package com.ag.springlogin.Controllers;

import com.ag.springlogin.Model.User;
import com.ag.springlogin.Repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    UserRepository userrepository;

    @GetMapping(path = "/")
    public String WelcomePage() {
        return "redirect:login";
    }

    @GetMapping(path = "/login")
    public String Login(Model model) {
        User user = new User("agoddino", "", "",1);
        model.addAttribute("title", "Login");
        return "login";
    }

    @GetMapping(path = "/login/signout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
