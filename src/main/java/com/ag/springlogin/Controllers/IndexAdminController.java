package com.ag.springlogin.Controllers;

import com.ag.springlogin.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexAdminController {

    @GetMapping(path = "/admin")
    public String admin(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:login";
        } else if (user.getTipo() == 1) {
            return "Admin/indexAdmin";
        } else {
            return "redirect:error/403";
        }
    }

    //Model
    @ModelAttribute("title")
    public String title() {
        return "Home Admin";
    }

    @ModelAttribute("home")
    public String home() {
        return "admin";
    }

    @ModelAttribute("user")
    public User user(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
