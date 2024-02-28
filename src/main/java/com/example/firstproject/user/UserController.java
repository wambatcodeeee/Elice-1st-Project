package com.example.firstproject.user;

import com.example.firstproject.user.entity.User;
import com.example.firstproject.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        userService.signup(userId, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        User loginUser = userService.login(user.getUserId(), user.getPassword());
        if (loginUser != null) {
            model.addAttribute("userId", user.getUserId());
            return "/board/boards";
        } else {
            return "user/login";
        }
    }
}
