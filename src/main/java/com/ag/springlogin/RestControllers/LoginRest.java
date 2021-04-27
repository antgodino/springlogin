package com.ag.springlogin.RestControllers;

import com.ag.springlogin.Model.User;
import com.ag.springlogin.Repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("login/rest")
public class LoginRest {

    @Autowired
    UserRepository userrepository;

    @PostMapping(path = "signin")
    public User LoginSubmit(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
                            HttpServletRequest request) {
        password = DigestUtils.md5Hex(password);
        User user = userrepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return user;
        }
        return null;
    }
}
