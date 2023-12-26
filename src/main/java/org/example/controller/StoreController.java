package org.example.controller;

import org.example.entity.Store;
import org.example.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("stores", storeService.getAll());
        return "store";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Store store = storeService.getById(id);
        model.addAttribute("store", store);
        return "store-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute Store store){
        storeService.create(store);
        return "redirect:/store";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        storeService.delete(id);
        return "redirect:/store";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("store") Store store) {
        storeService.update(store);
        return "redirect:/store";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getStore(@PathVariable("id") Long id, Model model) {
        Store store = storeService.getById(id);
        model.addAttribute("store", store);
        return "redirect:/store";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editStore(@ModelAttribute Store store) {
        storeService.update(store);
        return "redirect:/store";
    }
}
