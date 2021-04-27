package com.ag.springlogin.Controllers;

import com.ag.springlogin.Model.User;
import com.ag.springlogin.utilis.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexUserController {

    private PageParameters pageParameters = new PageParameters("admin", "Home Admin");

    @GetMapping(path = "/user")
    public String admin(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");

        //setting del model con i parametri standard
        Utils.pageModelInizializer(model, request, pageParameters);

        if (user == null) {
            return "redirect:login";
        } else if (user != null && user.getTipo() == 2) {
            return "User/indexUser";
        } else {
            return "redirect:error/403";
        }
    }

}
