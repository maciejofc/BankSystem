package pl.maciejowsky.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejowsky.banksystem.model.User;
import pl.maciejowsky.banksystem.service.AdminService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

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

}
