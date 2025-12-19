package com.quangminh.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.quangminh.laptopshop.service.UserService;

@Controller
public class DashboardController {
    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getDashboardPage(Model model) {
        model.addAttribute("countUsers", this.userService.countUsers());
        model.addAttribute("countProducts", this.userService.countProducts());
        model.addAttribute("countOrders", this.userService.countOrders());

        return "admin/dashboard/view";
    }
}
