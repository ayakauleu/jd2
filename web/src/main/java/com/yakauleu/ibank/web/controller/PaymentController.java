package com.yakauleu.ibank.web.controller;

import com.yakauleu.ibank.database.model.Currency;
import com.yakauleu.ibank.database.model.Payment;
import com.yakauleu.ibank.database.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentRepository repository;

    @ModelAttribute
    public void transferParams(Model model) {
        model.addAttribute("datefrom", LocalDate.now().minusYears(1));
        model.addAttribute("datetill", LocalDate.now());
        model.addAttribute("currencies", Currency.values());
    }

    @GetMapping("/payments")
    public String openPaymentsFormPage(Model model) {

        List<Payment> payments = repository.findAll();
        model.addAttribute("payments", payments);
        return "payments";
    }
}
