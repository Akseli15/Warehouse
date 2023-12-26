package org.example.controller;

import org.example.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.entity.Good;
import org.example.service.GoodService;

import java.util.List;

@Controller
@RequestMapping("/good")
public class GoodController {

    @Autowired
    GoodService goodService;
    @Autowired
    GoodRepository goodRepository;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("goods", goodService.getAll());
        return "good";
    }

    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Good good = goodService.getById(id);
        model.addAttribute("good", good);
        return "good";
    }

    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute Good good) {
        goodService.create(good);
        return "redirect:/good";
    }

    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        goodService.delete(id);
        return "redirect:/good";
    }

    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("good") Good good) {
        goodService.update(good);
        return "redirect:/good";
    }

    @Async
    @GetMapping("/edit/{id}")
    public String getGood(@PathVariable("id") Long id, Model model) {
        Good good = goodService.getById(id);
        model.addAttribute("good", good);
        return "good";
    }

    @Async
    @PostMapping("/edit/{id}")
    public String editGood(@ModelAttribute Good good) {
        goodService.update(good);
        return "redirect:/good";
    }

    @GetMapping("/unordered")
    public String showUnorderedGoods(Model model) {
        List<Object[]> unordered = goodRepository.getProductOrderVolume();
        model.addAttribute("unordered", unordered);
        return "unordered";
    }
}