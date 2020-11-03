package com.spring.bankingApp.web;

import com.spring.bankingApp.business.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BankController {
    @Autowired
    private Bank bank;

    @RequestMapping("/operations")
    private String index() {
        return "accounts";
    }
}
