package pl.maciejowsky.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejowsky.banksystem.service.UserService;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping("/profile/index")
    public String profile(Model model, Principal principal) {
        String currentlyLoggedUser = principal.getName();
        model.addAttribute("user", userService.getUserInformation(currentlyLoggedUser));
        return "profile/index";
    }

}
