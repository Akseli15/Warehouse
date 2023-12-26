package org.example.controller;


import org.example.entity.Invoice;
import org.example.entity.Zakaz;
import org.example.repository.OrderRepository;
import org.example.service.InvoiceService;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    OrderRepository orderRepository;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("invoices", invoiceService.getAll());
        return "order";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Zakaz zakaz = orderService.getById(id);
        model.addAttribute("order", zakaz);
        return "ordert-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute Zakaz zakaz,
                         @RequestParam("invoiceId") Long invoiceId,
                         @RequestParam("orderDate") LocalDate orderDate) {
        Invoice invoice = invoiceService.getById(invoiceId);
        zakaz.setInvoice(invoice);
        zakaz.setOrderDate(orderDate);
        orderService.create(zakaz);
        return "redirect:/order";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return "redirect:/order";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("order") Zakaz zakaz) {
        orderService.update(zakaz);
        return "redirect:/order";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getOrder(@PathVariable("id") Long id, Model model) {
        Zakaz zakaz = orderService.getById(id);
        model.addAttribute("order", zakaz);
        return "redirect:/order";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editOrder(@ModelAttribute Zakaz zakaz) {
        orderService.update(zakaz);
        return "redirect:/order";
    }

    @GetMapping("/volume")
    public String showUnorderedGoods(Model model) {
        List<Object[]> volume = orderRepository.getProductOrderVolume();
        model.addAttribute("volume", volume);
        return "volume";
    }

    @GetMapping("/needed")
    public String showRequiredQuantitiesOfGoods(Model model) {
        List<Object[]> needed = orderRepository.getRequiredQuantitiesForNextMonth();
        model.addAttribute("needed", needed);
        return "needed";
    }

    @GetMapping("/current/{orderId}")
    public String showCurrentOrderDetail(@PathVariable("orderId") Long orderId, Model model) {
        List<Object[]> current = orderRepository.getCurrentOrderDetail(orderId);
        model.addAttribute("current", current);
        return "current";
    }
}
