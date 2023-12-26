package org.example.controller;

import org.example.entity.Warehouse;
import org.example.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("warehouses", warehouseService.getAll());
        return "warehouse";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Warehouse warehouse = warehouseService.getById(id);
        model.addAttribute("warehouse", warehouse);
        return "warehouse-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute Warehouse warehouse){
        warehouseService.create(warehouse);
        return "redirect:/warehouse";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        warehouseService.delete(id);
        return "redirect:/warehouse";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("warehouse") Warehouse warehouse) {
        warehouseService.update(warehouse);
        return "redirect:/warehouse";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getWarehouse(@PathVariable("id") Long id, Model model) {
        Warehouse warehouse = warehouseService.getById(id);
        model.addAttribute("warehouse", warehouse);
        return "redirect:/warehouse";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editWarehouse(@ModelAttribute Warehouse warehouse) {
        warehouseService.update(warehouse);
        return "redirect:/warehouse";
    }
}
