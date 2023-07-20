package com.example.accounts.controller;


import com.example.accounts.entity.Account;
import com.example.accounts.entity.User;
import com.example.accounts.service.AuthService;
import com.example.accounts.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
@SessionAttributes("header_token")
@AllArgsConstructor
public class TransactionController {

    public TransactionService transactionService;
    private AuthService authService;

    @ModelAttribute("header_token")
    public int default_token() {
        return 0;
    }

    @GetMapping("")
    public String getTransactions(@ModelAttribute("header_token") int token, Model model) {
        if (!authService.checkToken(token))
            return "redirect:/auth/login";

        User user = authService.getUserByToken(token);
        model.addAttribute("accountNumber", user.getAccount().getNumber());
        model.addAttribute("transactions", transactionService.getAllTransactions(user.getAccount()));
        System.out.println(transactionService.getAllTransactions(user.getAccount()));
        model.addAttribute("header_token", token);
        return "transactions";
    }

    @GetMapping("/{id}")
    public String transactionDetails(@PathVariable("id") int id, @ModelAttribute("header_token") int token, Model model) {
        if (!authService.checkToken(token))
            return "redirect:/auth/login";

        model.addAttribute("transaction", transactionService.getTransactionById(id));
        model.addAttribute("header_token", token);
        return "transactionDetails";
    }


}
