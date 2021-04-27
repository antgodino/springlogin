package com.ag.springlogin.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Error {
    @GetMapping(path="/error/403")
    public String error403(){
        return "Errors/403";
    }
}
