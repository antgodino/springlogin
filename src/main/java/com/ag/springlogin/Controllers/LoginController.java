package com.ag.springlogin.Controllers;

import com.ag.springlogin.Repository.UserRepository;
import com.ag.springlogin.utilis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private PageParameters pageParameters = new PageParameters("login", "Login");

    @Autowired
    UserRepository userrepository;

    @GetMapping(path = "/")
    public String WelcomePage() {
        return "redirect:login";
    }

    @GetMapping(path = "/login")
    public String Login(Model model) {

        //setting del model con i parametri standard
        Utils.pageModelInizializer(model, pageParameters);
        return "login";
    }

    @GetMapping(path = "/login/signout")
    public String logout(HttpServletRequest request, Model model) {
        request.getSession().invalidate();

        //setting del model con i parametri standard
        Utils.pageModelInizializer(model, pageParameters);
        return "redirect:/login";
    }
}
