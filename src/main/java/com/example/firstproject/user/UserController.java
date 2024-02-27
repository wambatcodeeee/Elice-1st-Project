package com.example.firstproject.user;

import com.example.firstproject.user.entity.User;
import com.example.firstproject.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(String userId, String password) {
        userService.signup(userId, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, Model model) {
        User user = userService.login(userId, password);
        if (user != null) {
            model.addAttribute("userId", userId);
            return "/board/boards";
        } else {
            return "login";
        }
    }
}
