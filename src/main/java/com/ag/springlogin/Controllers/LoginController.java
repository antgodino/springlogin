package com.ag.springlogin.Controllers;

import com.ag.springlogin.Model.User;
import com.ag.springlogin.Sevice.UserService;
import com.ag.springlogin.securityConfig.MyUserDetailService;
import com.ag.springlogin.utilis.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    MyUserDetailService myUserDetailService;

    @GetMapping(path = "/")
    public String WelcomePage() {
        return "redirect:login";
    }

    @GetMapping(path = "/login")
    public String Login() {
        return "login";
    }

//    @GetMapping(path = "/login/signout")
//    public String logout(HttpServletRequest request, Model model) {
//        request.getSession().invalidate();
//        return "redirect:/login";
//    }

    @PostMapping(path = "/login/signin")
    @ResponseBody
    public User LoginSubmit(HttpSession session, Authentication authentication) {
        if (authentication != null) {
            User user = userService.getUserByUsername(authentication.getName());
            if (user != null) {
                session.setAttribute("user", user);
                return user;
            }
        }
        return null;
    }

    @PostMapping(path = "/login/firstacces")
    @ResponseBody
    public Response changePassword(@RequestParam(name = "oldpassword") String oldpassword,
                                   @RequestParam(name = "newpassword") String newpassword,
                                   @RequestParam(name = "newpassword2") String newpassword2,
                                   HttpSession session) {
        Response response = new Response(false, "");
        User user = (User) session.getAttribute("user");
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

    @GetMapping(path = "/login/firstacces")
    public String firstAccess() {
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
