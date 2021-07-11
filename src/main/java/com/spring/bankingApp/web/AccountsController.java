package com.spring.bankingApp.web;

import com.spring.bankingApp.business.Bank;
import com.spring.bankingApp.model.Account;
import com.spring.bankingApp.model.BankClient;
import com.spring.bankingApp.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

@Controller
public class AccountsController {
    @Autowired
    private Bank bank;

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping({"/accounts"})
    public String findAccount() {
        return "accounts-client";
    }

    @GetMapping("/check_your_account")
    public String checkYourAccount(@AuthenticationPrincipal BankClient bankClient, Model model)
    {
        model.addAttribute("bankClient", bankClient);
        return "accounts-client-checking";
    }

    @GetMapping("/check_client_account")
    public String checkClientAccount(Model model, Long idAccount)
    {
        try {
            var account = bank.checkAccount(idAccount);
            var pageOperations = bank.listOperations(idAccount, 0, 7);
            model.addAttribute("listOperations", pageOperations.getContent());
            model.addAttribute("account", account);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "accounts-client-checked";
    }

    @RequestMapping("/checkAccount")
    public String check(Model model, Long idAccount) {
        try {
            var account = bank.checkAccount(idAccount);
            var pageOperations = bank.listOperations(idAccount, 0, 7);
            model.addAttribute("listOperations", pageOperations.getContent());
            model.addAttribute("account", account);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "accounts-admin";
    }

    @RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
    public String saveOperation(Model model, String typeOperation, Long idAccount, double amount, Long toIdAccount) throws Exception {
        try {
            if (Objects.equals(typeOperation, "DEP")) {
                bank.depositMoney(idAccount, amount);
            } else if (Objects.equals(typeOperation, "WTR")) {
                bank.withdrawMoney(idAccount, amount);
            } else if (Objects.equals(typeOperation, "TRS")) {
                bank.transferMoney(idAccount, toIdAccount, amount);
            }
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "redirect:/checkAccount?idAccount=" + idAccount;
    }

    @RequestMapping(value = "/saveOperationOfClient", method = RequestMethod.POST)
    public String saveOperationOfClient(Model model, String typeOperation, Long idAccount, double amount, Long toIdAccount) throws Exception {
        try {
            if (Objects.equals(typeOperation, "DEP")) {
                bank.depositMoney(idAccount, amount);
            } else if (Objects.equals(typeOperation, "WTR")) {
                bank.withdrawMoney(idAccount, amount);
            } else if (Objects.equals(typeOperation, "TRS")) {
                bank.transferMoney(idAccount, toIdAccount, amount);
            }
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "redirect:/check_client_account?idAccount=" + idAccount;
    }


}
