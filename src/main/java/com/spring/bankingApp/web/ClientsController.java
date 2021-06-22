package com.spring.bankingApp.web;


import com.spring.bankingApp.business.AccountService;
import com.spring.bankingApp.business.ClientService;
import com.spring.bankingApp.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientsController {

    private static final String VIEWS_CLIENT_CREATE_OR_UPDATE_FORM = "/signIn";

    private final ClientService clientService;
    private final AccountService accountService;


    public ClientsController(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @RequestMapping("/signIn")
    public String index() {
        return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/signIn")
    public String initCreationForm(Model model) {
        model.addAttribute("client", Client.builder().build());
        return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/signIn")
    public String processCreationForm(Client client, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            var savedClient = clientService.save(client);
            var accountFound = accountService.findById(savedClient.getAccount().getIdAccount());
            accountFound.setClient(savedClient);
            accountService.save(accountFound);
            return "redirect:/signIn/" + savedClient.getId();
        }
    }


}
