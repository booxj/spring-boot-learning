package com.springboot.validation.customized.web.controller;

import com.springboot.validation.customized.annotation.I18n;
import com.springboot.validation.customized.domain.validator.Merchant;
import com.springboot.validation.customized.domain.validator.Shop;
import com.springboot.validation.customized.domain.validator.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DashboardController.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/10 12:03
 * @version:
 */
//@I18n("dashboard")
@Controller
@RequestMapping("dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard(Model model) {
        List<Merchant> merchants = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Merchant merchant = new Merchant();
            merchants.add(merchant);
        }
        List<Shop> shops = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Shop shop = new Shop();
            shops.add(shop);
        }
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            users.add(user);
        }
        model.addAttribute("merchants", merchants);
        model.addAttribute("shops", shops);
        model.addAttribute("users", users);
        return "system/dashboard";
    }
}
