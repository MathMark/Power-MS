package com.pet.mailSender.controllers;

import com.pet.mailSender.dto.AccountRequest;
import com.pet.mailSender.model.Account;
import com.pet.mailSender.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ModelAndView getAllCampaigns(Model model) {
        List<Account> accounts = accountService.getAccounts();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accounts);
        modelAndView.setViewName("accounts");

        return modelAndView;
    }

    @GetMapping("/addAccount")
    public ModelAndView addAccountForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", new Account());
        modelAndView.setViewName("addAccount");

        return modelAndView;
    }

    @PostMapping("/saveAccount")
    public ModelAndView testConnection(@ModelAttribute("account") @Validated AccountRequest accountRequest, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addAccount");
        } else {
            boolean saved = accountService.save(accountRequest);
            if (saved) {
                accountService.save(accountRequest);
                modelAndView.setViewName("redirect:/accounts");
            } else {
                modelAndView.addObject("connectionStatus", "Wrong credentials");
                modelAndView.addObject("account", accountRequest);
                modelAndView.setViewName("addAccount");
            }
        }
        return modelAndView;
    }
}
