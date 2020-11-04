package com.spring.bankingApp.web;

import com.spring.bankingApp.business.Bank;
import com.spring.bankingApp.model.Account;
import com.spring.bankingApp.model.Operation;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BankController {
    @Autowired
    private Bank bank;

    @RequestMapping("/operations")
    public String index() {
        return "operations";
    }

    @RequestMapping("/checkAccount")
    public String check(Model model, String idAccount) {
        try {
            Account account = bank.checkAccount(idAccount);
            Page<Operation> pageOperations = bank.listOperations(idAccount, 0, 7);
            model.addAttribute("listOperations", pageOperations.getContent());
            System.out.println(pageOperations);
            model.addAttribute("account", account);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "operations";
    }

    @RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
    public String saveOperation(Model model, String typeOperation, String idAccount, double amount, String toIdAccount) throws Exception {
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


}
