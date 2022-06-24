package pl.maciejowsky.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.maciejowsky.banksystem.dao.UserDAO;
import pl.maciejowsky.banksystem.model.AccountType;
import pl.maciejowsky.banksystem.model.User;
import pl.maciejowsky.banksystem.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


@Controller
public class HomeController {
    //    private Set<AccountType> flyingAnimals = EnumSet.of( REGULAR , ENTEPRENEUR );
    private List<String> listAccountsType = Arrays.asList("REGULAR", "ENTREPRENEUR");
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String goToIndex() {
        return "index";
    }

    @RequestMapping("/login")
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String goToRegisterPage(Model model) {
        model.addAttribute("formData", new User());

        model.addAttribute("listAccountsType", listAccountsType);

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegisterUser(@Valid @ModelAttribute("formData") User user,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listAccountsType", listAccountsType);

            return "register";
        }
        if (userService.registerUser(user)) {
            System.out.println("UDALO SIE ZAREJESTROWAC");
            return "redirect:/login?registerSuccess";
        } else {
            model.addAttribute("emailExists", "This email already exists");

            return "register";
        }


    }

    @RequestMapping("/logout-success")
    public String goToLogoutPage(Model model) {
        model.addAttribute("loggedOut","You have benn logged out");
        return "index";
    }
}
