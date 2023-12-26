package org.example.controller;


import org.example.entity.WarehouseSupplies;
import org.example.service.StoreService;
import org.example.service.WarehouseService;
import org.example.service.WarehouseSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouse_supplies")
public class WarehouseSuppliesController {

    @Autowired
    WarehouseSuppliesService warehouseSuppliesService;
    @Autowired
    StoreService storeService;
    @Autowired
    WarehouseService warehouseService;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("warehouseSuppliess", warehouseSuppliesService.getAll());
        model.addAttribute("stores", storeService.getAll());
        model.addAttribute("warehouses", warehouseService.getAll());
        return "warehouse_supplies";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        WarehouseSupplies warehouseSupplies = warehouseSuppliesService.getById(id);
        model.addAttribute("warehouseSupplies", warehouseSupplies);
        return "warehouse-supplies-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute WarehouseSupplies warehouseSupplies){
        warehouseSuppliesService.create(warehouseSupplies);
        return "redirect:/warehouse_supplies";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        warehouseSuppliesService.delete(id);
        return "redirect:/warehouse_supplies";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("warehouseSupplies") WarehouseSupplies warehouseSupplies) {
        warehouseSuppliesService.update(warehouseSupplies);
        return "redirect:/warehouse_supplies";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getWarehouseSupplies(@PathVariable("id") Long id, Model model) {
        WarehouseSupplies warehouseSupplies = warehouseSuppliesService.getById(id);
        model.addAttribute("warehouseSupplies", warehouseSupplies);
        return "redirect:/warehouse_supplies";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editWarehouseSupplies(@ModelAttribute WarehouseSupplies warehouseSupplies) {
        warehouseSuppliesService.update(warehouseSupplies);
        return "redirect:/warehouse_supplies";
    }
}
