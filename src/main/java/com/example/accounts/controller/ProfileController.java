package com.example.accounts.controller;


import com.example.accounts.entity.User;
import com.example.accounts.service.AuthService;
import com.example.accounts.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/profile")
@SessionAttributes("header_token")
@AllArgsConstructor
public class ProfileController {

    private final AuthService authService;
    private final UserService userService;

    @ModelAttribute("header_token")
    public int default_token() {
        return 0;
    }

    @GetMapping("")
    public String profile(
            @ModelAttribute("header_token") int token, Model model) {

        if (!authService.checkToken(token))
            return "redirect:/auth/login";

        User user = authService.getUserByToken(token);
        model.addAttribute("user", user);
        model.addAttribute("header_token", token);
        return "userProfile";
    }
}
