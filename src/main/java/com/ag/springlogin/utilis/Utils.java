package com.ag.springlogin.utilis;

import com.ag.springlogin.Controllers.PageParameters;
import com.ag.springlogin.Model.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static void pageModelInizializer(Model model, HttpServletRequest request, PageParameters pageParameters) {
        model.addAttribute("home", pageParameters.getHome());
        if (request != null)
            model.addAttribute("user", (User)request.getSession().getAttribute("user"));
        model.addAttribute("title", pageParameters.getTitle());
    }
    public static void pageModelInizializer(Model model, PageParameters pageParameters) {
        pageModelInizializer(model, null, pageParameters);
    }
}
