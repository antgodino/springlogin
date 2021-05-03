package com.ag.springlogin.RestControllers;

import com.ag.springlogin.Model.User;
import com.ag.springlogin.Sevice.UserService;
import com.ag.springlogin.utilis.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("login/rest")
public class LoginRest {

    @Autowired
    UserService userService;

    @PostMapping(path = "signin")
    public User LoginSubmit(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
                            HttpServletRequest request) {
        password = DigestUtils.md5Hex(password);
        User user = userService.getUser(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return user;
        }
        return null;
    }

    @PostMapping(path = "firstacces")
    public Response changePassword(@RequestParam(name = "oldpassword") String oldpassword,
                                   @RequestParam(name = "newpassword") String newpassword,
                                   @RequestParam(name = "newpassword2") String newpassword2,
                                   HttpServletRequest request) {
        Response response = new Response(false, "");
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (DigestUtils.md5Hex(oldpassword).equals(user.getPassword())) {
                if (newpassword.equals(newpassword2)) {
                    user.setPassword(DigestUtils.md5Hex(newpassword));
                    user.setStato(1);
                    userService.save(user);
                    response.setResult(true);
                } else {
                    response.setMessage("Errore: le due password non coincidono.");
                }
            } else {
                response.setMessage("Errore: la vecchia password non coincide.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Errore: non &egrave; stato possibile salvare la nuova Password.");
        }

        return response;
    }
}
