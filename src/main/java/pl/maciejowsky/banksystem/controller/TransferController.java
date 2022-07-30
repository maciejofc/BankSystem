package pl.maciejowsky.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.maciejowsky.banksystem.model.Account;
import pl.maciejowsky.banksystem.model.Transfer;
import pl.maciejowsky.banksystem.service.AccountService;
import pl.maciejowsky.banksystem.service.TransferService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TransferController {
    private final List<String> listTransferTypes = Arrays.asList(new String[]{"NORMAL",
            "EXPRESS", "INSTANT"});

    private Map<String, BigDecimal> listNumberAccountsWithFunds(String loggedUserEmail) {
        Map<String, BigDecimal> numberAccountsWithFunds =
                new HashMap<>();
        String currentlyLoggedUserEmail = loggedUserEmail;
        List<Account> availableAccounts = accountService.getAllAccounts(currentlyLoggedUserEmail);
        for (int i = 0; i < availableAccounts.size(); i++) {
            String accNumber = availableAccounts.get(i).getAccountNumber();
            BigDecimal funds = availableAccounts.get(i).getBalance();
            numberAccountsWithFunds.put(accNumber, funds);
        }
        return numberAccountsWithFunds;
    }

    @Autowired
    private TransferService transferService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/user/transfer")
    public String goToTransferSection() {
        return "user/transfer";
    }

    @RequestMapping("/user/transfer/make")
    public String goToMakeTransfer(Model model, Principal principal) {
        String currentlyLoggedUser = principal.getName();
        model.addAttribute("listTransferTypes", listTransferTypes);
        model.addAttribute("formData", new Transfer());
        model.addAttribute("listAvailableAccounts", listNumberAccountsWithFunds(currentlyLoggedUser));
        model.addAttribute("errors", new String[]{"", ""});
        return "user/make-transfer";
    }

    @RequestMapping(value = "/user/transfer/make", method = RequestMethod.POST)
    public String makeTransfer(@ModelAttribute("formData") Transfer transfer,
                               BindingResult bindingResult,
                               Model model, Principal principal) {
        String[] errorsDueToIncorrectData = transferService.validateTransfer(transfer);
        //first validation
        if (bindingResult.hasErrors()) {
            model.addAttribute("listTransferTypes", listTransferTypes);
            model.addAttribute("listAvailableAccounts", listNumberAccountsWithFunds(principal.getName()));
            System.out.println("errors");
            return "user/make-transfer";
        }
        //second validation
        if (errorsDueToIncorrectData == null) {
            model.addAttribute("listTransferTypes", listTransferTypes);
            model.addAttribute("listAvailableAccounts", listNumberAccountsWithFunds(principal.getName()));
            transferService.makePassableSpecificTransfer(transfer);
            return "redirect:/user/transfer/make?transferSuccess";

        } else {
            model.addAttribute("errors", errorsDueToIncorrectData);
            model.addAttribute("listTransferTypes", listTransferTypes);
            model.addAttribute("listAvailableAccounts", listNumberAccountsWithFunds(principal.getName()));
            return "make-transfer";
        }

    }


    @RequestMapping("/user/transfer/history")
    public String goToTransferHistory(Model model,Principal principal) {
        String email = principal.getName();
        List<List<Transfer>> fullHistory = transferService.getTransferHistoryForUser(email);
        model.addAttribute("sentHistory",fullHistory.get(0));
        model.addAttribute("receivedHistory",fullHistory.get(1));
        return "user/transfer-history";
    }


}
