package com.secured.cbank.controller;

import com.secured.cbank.entity.Amount;
import com.secured.cbank.entity.Card;
import com.secured.cbank.repository.CardRepository;
import com.secured.cbank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank/")
public class BankController {
    private BankService bankService;

    @Autowired
    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @GetMapping("local/hub")
    public String hub(){
        return "hub";
    }

    @GetMapping("success")
    public String success(){
        return "success";
    }

    @GetMapping("error")
    public String error(){return "error";}

    @GetMapping("banklogin")
    public String login(Model model){
        model.addAttribute("loginRequest", new Card());
        return "login";
    }

    @GetMapping("local/refill")
    public String refillModel(Model model){
        model.addAttribute("refillRequest", new Amount());
        return "refill";
    }

    @PostMapping("local/refill")
    public String refill(@ModelAttribute("refillRequest")Amount amount) throws Exception {
        boolean refillSuccessed = bankService.refillCard(amount.getAmount());

        return refillSuccessed ? "success" : "error";
    }

    @GetMapping("local/transfer")
    public String transferModel(Model model){
        model.addAttribute("transferRequest", new Amount());
        return "transfer";
    }

    @PostMapping("local/transfer")
    public String transfer(@ModelAttribute("refillRequest")Amount amount) throws Exception {
        boolean transferSuccessed = bankService.transferToCard(amount.getAmount(), amount.getNumber());

        return transferSuccessed ? "success" : "error";
    }

    @GetMapping("local/view-card")
    public String viewModel(Model model) throws Exception {
        String amount = String.valueOf(bankService.checkAmountOnCard()), cardNumber = bankService.getNumber();

        model.addAttribute("amount", amount);
        model.addAttribute("cardNumber", cardNumber);

        return "view-amount";
    }
}
