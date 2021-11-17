package com.secured.cbank.controller;

import com.secured.cbank.entity.Card;
import com.secured.cbank.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdministratorController {
    private AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService){
        this.administratorService = administratorService;
    }

    @GetMapping("add-card")
    public String addModelCard(Model model){
        model.addAttribute("addRequest", new Card());
        return "add-card";
    }

    @PostMapping("add-card")
    public String addCard(@ModelAttribute("addRequest") Card card){
        boolean isAdded = administratorService.addCard(card);

        return isAdded ? "success" : "error";
    }
}
