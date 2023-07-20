package com.example.accounts.controller;


import com.example.accounts.entity.Account;
import com.example.accounts.model.TransferDto;
import com.example.accounts.service.AccountService;
import com.example.accounts.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
@SessionAttributes("header_token")
@AllArgsConstructor
public class AccountController {
    AccountService accountService;
    AuthService authService;

    @ModelAttribute("header_token")
    public int default_token() {
        return 0;
    }

    @GetMapping("")
    public String account(
            @ModelAttribute("header_token") int token, Model model) {
        if (!authService.checkToken(token)) {
            return "redirect:/auth/login";
        }
        Account account = authService.getAccountByToken(token);
        model.addAttribute("account", account);
        model.addAttribute("header_token", token);
        return "userAccount";
    }

    @GetMapping("/transfer")
    public String transfer(Model model,
                           @ModelAttribute("header_token") int token, @ModelAttribute("transferDto") TransferDto transferDto,
                           @ModelAttribute("error") String error) {
        if (!authService.checkToken(token))
            return "redirect:/auth/login";

        Account account = authService.getAccountByToken(token);
        model.addAttribute("account", account);
        model.addAttribute("header_token", token);

        if(authService.isAccountEligible(account)) {
            model.addAttribute("error", "account error");
            return "userAccount";
        }


        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("transferDto") @Valid TransferDto transferDto,
                           BindingResult bindingResult,
                           @ModelAttribute("header_token") int token,
                            Model model) {
        if (!authService.checkToken(token)) {
            return "redirect:/auth/login";
        }
        Account account = authService.getAccountByToken(token);
        model.addAttribute("account", account);
        validateInput(transferDto.getAccountNumber(), transferDto.getSum(), bindingResult, account);
        if (bindingResult.hasErrors())
            return "transfer";

        accountService.transfer(account, accountService.getAccount(
                transferDto.getAccountNumber()), transferDto.getSum());
        model.addAttribute("header_token", token);
        return "redirect:/account";
    }

    private void validateInput(String accountNumber, int sum, BindingResult bindingResult, Account account) {
        if (accountNumber == "")
            bindingResult.addError(new ObjectError("globalError", "Account number can't be empty"));
        else if (accountService.getAccount(accountNumber) == null || account.getNumber().equals(accountNumber))
            bindingResult.addError(new ObjectError("globalError", "Incorrect account number"));

        if (sum == 0)
            bindingResult.addError(new ObjectError("globalError", "Sum must be grater than 0"));

        if (account.getMoneyAmount() < sum)
            bindingResult.addError(new ObjectError("globalError", "Sum must be less than account funds"));
    }
}
