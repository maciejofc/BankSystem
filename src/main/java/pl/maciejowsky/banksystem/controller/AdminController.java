package pl.maciejowsky.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.maciejowsky.banksystem.model.BankConfig;
import pl.maciejowsky.banksystem.model.User;
import pl.maciejowsky.banksystem.service.AdminService;
import pl.maciejowsky.banksystem.service.BankConfigService;
import pl.maciejowsky.banksystem.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    BankConfigService bankConfigService;

    @Autowired
    UserService userService;

    @RequestMapping("/admin/users")
    public String getAllUsers(Model model) {
        List<User> users = adminService.getAllUsersAndManagers();
        model.addAttribute("usersList", users);
        return "admin/users";
    }

    @RequestMapping("/admin/index")
    public String goToAdminIndexPage() {
        return "admin/index";
    }

    @RequestMapping("/admin/config")
    public String goToAdminConfig(Model model) {

        model.addAttribute("currentConfig", bankConfigService.getBankConfiguration());
        model.addAttribute("author", bankConfigService.getAuthorOfConfiguration());
        return "admin/config";

    }

    @RequestMapping(value = "/admin/config", method = RequestMethod.POST)
    public String createNewCurrentConfig(@ModelAttribute("formData") BankConfig bankConfig,
                                         BindingResult bindingResult,
                                         Model model, Principal principal) {
        String email = principal.getName();

        if (bindingResult.hasErrors()) {
            model.addAttribute("currentConfig", bankConfigService.getBankConfiguration());
            model.addAttribute("author", bankConfigService.getAuthorOfConfiguration());
            return "admin/config";

        } else {
            int authorId = userService.getUserInformation(email).getId();
            bankConfig.setFkAdminId(authorId);
            bankConfigService.createNewConfig(bankConfig, authorId);
            return "redirect:/admin/config?configSuccess";

        }
    }


}
