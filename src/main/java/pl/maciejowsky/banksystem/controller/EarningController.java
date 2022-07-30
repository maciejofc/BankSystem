package pl.maciejowsky.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.maciejowsky.banksystem.Utility;
import pl.maciejowsky.banksystem.enums.SpecificAccount;
import pl.maciejowsky.banksystem.model.Account;
import pl.maciejowsky.banksystem.model.BankConfig;
import pl.maciejowsky.banksystem.model.Deposit;
import pl.maciejowsky.banksystem.service.AccountService;
import pl.maciejowsky.banksystem.service.EarningService;
import pl.maciejowsky.banksystem.service.UserService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
public class EarningController {

    @Autowired
    UserService userService;
    @Autowired
    EarningService earningService;

    @Autowired
    AccountService accountService;


    @ModelAttribute("availableFunds")
    public BigDecimal getAvailableFunds(Principal principal) {
        List<Account> listOfAllAccounts = accountService.getAllAccounts(principal.getName());
        return listOfAllAccounts.stream().filter(acc -> acc.getSpecificAccount() == SpecificAccount.NORMAL).findFirst().get().getBalance();

    }


    @ModelAttribute("depositInfo")
    public BankConfig getDepositInfo() {
        return earningService.getDepositInformation();
    }

    @ModelAttribute("userId")
    public int getUserId(Principal principal) {
        return userService.getUserInformation(principal.getName()).getId();

    }


    @ModelAttribute("deposit")
    public Deposit getDeposit() {
        return new Deposit();
    }

    @RequestMapping("/user/earning")
    public String goToEarning() {

        return "user/earning";
    }

    @RequestMapping("/user/earning/history")
    public String goToEarningHistory(Model model) {
        int userId = (int) model.asMap().get("userId");
        model.addAttribute("allEarnedMoney", earningService.getAllEarnedMoney(userId));
        model.addAttribute("depositHistory", earningService.getHistoryForUser(userId));
        return "user/deposit-history";
    }

    @RequestMapping("/user/earning/ask")
    public String goToEarningAskForMoney(Model model) {

        return "user/ask";
    }


    @RequestMapping("/user/earning/deposit")
    public String goToEarningDeposit(Model model, Principal principal) {
        int userId = userService.getUserInformation(principal.getName()).getId();
        Deposit activeDeposit = earningService.getActiveDeposit(userId);
        model.addAttribute("activeDeposit", activeDeposit);
        if (activeDeposit != null)
            model.addAttribute("moneyToReturn", Utility.roundBigDecimalTo2Places(earningService.calculateMoneyToReturn(activeDeposit)));
        return "user/make-deposit";
    }

    @RequestMapping(value = "/user/earning/deposit", method = RequestMethod.POST)
    public String makeDeposit(@ModelAttribute("deposit") Deposit deposit, Model model) {
        BigDecimal availableFunds = (BigDecimal) model.asMap().get("availableFunds");
        int userId = (int) model.asMap().get("userId");
        deposit.setUserId(userId);

        String possibleError = earningService.validateDeposit(deposit, availableFunds);
        if (possibleError == null) {
            earningService.makeDeposit(deposit);
            model.addAttribute("activeDeposit", deposit);
            return "redirect:/user/earning/deposit?depositSuccess";
        }
        model.addAttribute("fundsError", possibleError);
        return "user/make-deposit";


    }

    @RequestMapping(value = "/user/earning/deposit/cancel")
    public String cancelDeposit(Model model) {

        int userId = (int) model.asMap().get("userId");
        Deposit activeDeposit = earningService.getActiveDeposit(userId);
        String possibleError = earningService.cancelDeposit(activeDeposit);
        if (possibleError != null) {
            model.addAttribute("cancelError", possibleError);
            model.addAttribute("activeDeposit", activeDeposit);
            return "user/make-deposit";
        }

        return "redirect:/user/earning/deposit";


    }

    @RequestMapping(value = "/user/earning/deposit/finalize")
    public String finalizeDeposit(Model model) {
        int userId = (int) model.asMap().get("userId");
        Deposit activeDeposit = earningService.getActiveDeposit(userId);
        earningService.finalizeDeposit(activeDeposit);
        return "redirect:/user/earning/deposit";
    }

}
