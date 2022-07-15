package pl.maciejowsky.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejowsky.banksystem.model.Account;
import pl.maciejowsky.banksystem.service.AccountService;

import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/user/accounts")
    public String getAllAccounts(Principal principal, Model model) {
        String searchEmailConstraint = principal.getName();
        List<Account> accounts = accountService.getAllAccounts(searchEmailConstraint);
        model.addAttribute("accountList", accounts);
        return "user/accounts";
    }
}
