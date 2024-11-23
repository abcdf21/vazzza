package com.example.finalproject.controller;


import com.example.finalproject.models.UserModel;
import com.example.finalproject.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class usersController {

    boolean isAuthorization = false;
    String message;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/authorization")
    public String authorization() {
        return "authorizationPage";
    }

    @GetMapping("/main/logout")
    public String logout(HttpSession session) {
        isAuthorization = false;
        session.removeAttribute("currentUser");
        return "redirect:/authorization";
    }
    @GetMapping("/main")
    public String mainM(Model model, HttpSession session) {
        UserModel currentUser = (UserModel) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isAuthorization", currentUser != null);
        return "mainMenu";
    }

    @PostMapping("/authorization/checkData")
    public String checkData(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session
    )
    {

        List<UserModel> users = userRepository.findAll();
        for (UserModel user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                session.setAttribute("currentUser", user);
                return "redirect:/main";
            }
        }
        return "redirect:/authorization";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("message", message);
        message = "";
        return "registrationPage";
    }

    @PostMapping("/registration/addUser")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session)
    {
        if(isBusy(username, session)) {
            return "redirect:/registration";
        }
        UserModel user = new UserModel(username, password);
        userRepository.save(user);
        return "redirect:/authorization";
    }


    @GetMapping("/main/profile")
    public String profile(Model model, HttpSession session) {
        UserModel currentUser = (UserModel) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("message", message);
        message = "";
        return "profile";
    }

    @PostMapping("/main/profile/update")
    public String updateProfile(
            @RequestParam String username,
            @RequestParam String password, HttpSession session
    ){
        if(!isBusy(username, session)){
            UserModel currentUser = (UserModel) session.getAttribute("currentUser");

            currentUser.setUsername(username);
            currentUser.setPassword(password);

            userRepository.save(currentUser);

            session.setAttribute("currentUser", currentUser);
        }


        return "redirect:/main/profile";
    }

    @GetMapping("/main/profile/delete")
    public String deleteProfile(HttpSession session) {
        UserModel currentUser = (UserModel) session.getAttribute("currentUser");
        session.removeAttribute("currentUser");
        userRepository.delete(currentUser);
        return "redirect:/authorization";
    }

    private boolean isBusy(String username, HttpSession session) {
        List<UserModel> users = userRepository.findAll();
        UserModel currentUser = (UserModel) session.getAttribute("currentUser");
        for (UserModel user : users) {
            if (user.getUsername().equals(username)) {
                if (currentUser!= null && user.getUsername().equals(currentUser.getUsername())) {
                    return false;
                }
                else{
                    message = "Логин занят";
                    return true;
                }
            }
        }
        return false;
    }

}
