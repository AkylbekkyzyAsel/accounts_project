package com.example.accounts.controller;


import com.example.accounts.model.UserCredentialDto;
import com.example.accounts.service.AuthService;
import com.example.accounts.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
@SessionAttributes("header_token")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @ModelAttribute("header_token")
    public int default_token() {
        return 0;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("userCred") UserCredentialDto userCred) {
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin( @Valid @ModelAttribute("userCred") UserCredentialDto userCred,
                             BindingResult bindingResult, Model model) {
        validateInput(userCred.getUsername(), userCred.getPassword(), bindingResult);
        if (bindingResult.hasErrors())
            return "login";

        //sing in return value generated token
        int token = authService.signIn(userCred.getUsername(), userCred.getPassword());
        if (token != 0) {
            authService.createSession(token, userService.getByUsername(userCred.getUsername()));
            model.addAttribute("header_token", token);
            return "redirect:/profile";
        }
        bindingResult.addError(new ObjectError("globalError", "Username or password is incorrect"));
        return "login";
    }

    @GetMapping("/logout")
    public String logout(@ModelAttribute("header_token") int token, Model model) {
        if (authService.checkToken(token))
            authService.deactivateToken(token);
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("userCred") UserCredentialDto userCred) {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("userCred") @Valid UserCredentialDto userCred,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "register";

        if (!userService.createUser(userCred.getUsername(), userCred.getPassword())) {
            model.addAttribute("usernameError", "User with this username already exists!");
            return "register";
        } else
            return "redirect:/profile";
    }

    private void validateInput(String username, String password, BindingResult bindingResult) {
        if (username == "")
            bindingResult.addError(new ObjectError("globalError", "Username can't be empty"));

        if (password == "")
            bindingResult.addError(new ObjectError("globalError", "Password can't be empty"));
    }
}

