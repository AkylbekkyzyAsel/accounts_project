package com.example.accounts.controller;

import com.example.accounts.entity.User;
import com.example.accounts.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("header_token")
@AllArgsConstructor
public class Page {

    private AuthService authService;

    @ModelAttribute("header_token")
    public int default_token() {
        return 0;
    }

    @GetMapping()
    public String index(
            @ModelAttribute("header_token") int token, Model model) {
        if (!authService.checkToken(token))
            return "redirect:/auth/login";

        return "redirect:/profile";
    }
}