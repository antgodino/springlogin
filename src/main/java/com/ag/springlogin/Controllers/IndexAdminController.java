package com.ag.springlogin.Controllers;

import com.ag.springlogin.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexAdminController {
    @GetMapping(path = "/admin")
    public String admin(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        if (user != null && user.getTipo() == 1) {
            return "Admin/indexAdmin";
        } else {
            return "redirect:error/403";
        }
    }
}
