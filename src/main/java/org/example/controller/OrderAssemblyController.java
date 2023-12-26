package org.example.controller;

import org.example.entity.OrderAssembly;
import org.example.entity.Store;
import org.example.entity.Zakaz;
import org.example.service.OrderAssemblyService;
import org.example.service.OrderService;
import org.example.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order_assembly")
public class OrderAssemblyController {

    @Autowired
    OrderAssemblyService orderAssemblyService;
    @Autowired
    StoreService storeService;
    @Autowired
    OrderService orderService;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("orderAssemblies", orderAssemblyService.getAll());
        model.addAttribute("stores", storeService.getAll());
        model.addAttribute("orders", orderService.getAll());
        return "order_assembly";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        OrderAssembly orderAssembly = orderAssemblyService.getById(id);
        model.addAttribute("orderAssembly", orderAssembly);
        return "order-assembly-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute OrderAssembly orderAssembly,
                         @RequestParam("storeName") String storeName,
                         @RequestParam("orderId") Long orderId){
        Store store = storeService.getByStoreName(storeName);
        Zakaz zakaz = orderService.getById(orderId);
        orderAssembly.setStore(store);
        orderAssembly.setZakaz(zakaz);
        orderAssemblyService.create(orderAssembly);
        return "redirect:/order_assembly";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderAssemblyService.delete(id);
        return "redirect:/order_assembly";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("orderAssembly") OrderAssembly orderAssembly) {
        orderAssemblyService.update(orderAssembly);
        return "redirect:/order_assembly";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getOrderAssembly(@PathVariable("id") Long id, Model model) {
        OrderAssembly orderAssembly = orderAssemblyService.getById(id);
        model.addAttribute("orderAssembly", orderAssembly);
        return "redirect:/order_assembly";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editOrderAssembly(@ModelAttribute OrderAssembly orderAssembly) {
        orderAssemblyService.update(orderAssembly);
        return "redirect:/order_assembly";
    }
}
