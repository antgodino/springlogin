package com.ag.springlogin.Controllers;

import com.ag.springlogin.Model.User;
import com.ag.springlogin.Sevice.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexAdminController {

    @Autowired
    FileService fileService;

    @GetMapping(path = "/admin")
    public String admin() {
        return "Admin/indexAdmin";
    }

    @PostMapping(path = "/admin")
    public String addUser(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email,
                          @RequestParam("file") MultipartFile[] file, HttpSession session) {
        User user = (User) session.getAttribute("user");
        fileService.multipleUploadFile(file, "C:/mnt");
        return "Admin/indexAdmin";
    }

    @GetMapping(value = "/admin/file/{filePath}/**")
    @ResponseBody
    public HttpEntity<byte[]> downloadFile(HttpServletRequest request) {
        String fileName = request.getRequestURL().toString().split("file/")[1];
        try {
            return fileService.showFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
