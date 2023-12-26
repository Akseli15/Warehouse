package org.example.controller;

import org.example.entity.Invoice;
import org.example.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("invoices", invoiceService.getAll());
        return "invoice";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Invoice invoice = invoiceService.getById(id);
        model.addAttribute("invoice", invoice);
        return "invoice-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute Invoice invoice){
        invoiceService.create(invoice);
        return "redirect:/invoice";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        invoiceService.delete(id);
        return "redirect:/invoice";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("invoice") Invoice invoice) {
        invoiceService.update(invoice);
        return "redirect:/invoice";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getInvoice(@PathVariable("id") Long id, Model model) {
        Invoice invoice = invoiceService.getById(id);
        model.addAttribute("invoice", invoice);
        return "redirect:/invoice";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editInvoice(@ModelAttribute Invoice invoice) {
        invoiceService.update(invoice);
        return "redirect:/invoice";
    }
}
