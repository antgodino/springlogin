package com.ag.springlogin.Controllers;

import com.ag.springlogin.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexUserController {
    @GetMapping(path = "/user")
    public String admin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        if (user != null && user.getTipo() == 2) {
            return "User/indexUser";
        } else {
            return "redirect:error/403";
        }
    }
}
