package org.example.controller;

import org.example.entity.Good;
import org.example.entity.Shipment;
import org.example.entity.Warehouse;
import org.example.repository.ShipmentRepository;
import org.example.service.GoodService;
import org.example.service.ShipmentService;
import org.example.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    ShipmentService shipmentService;
    @Autowired
    GoodService goodService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    ShipmentRepository shipmentRepository;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("shipments", shipmentService.getAll());
        model.addAttribute("warehouses", warehouseService.getAll());
        model.addAttribute("goods",goodService.getAll());
        return "shipment";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Shipment shipment = shipmentService.getById(id);
        model.addAttribute("shipment", shipment);
        return "shipment-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute Shipment shipment,
                         @RequestParam("goodName") String goodName,
                         @RequestParam("warehouseName") String warehouseName){
        Good good = goodService.getByGoodName(goodName);
        Warehouse warehouse = warehouseService.getByWarehouseName(warehouseName);
        shipment.setGood(good);
        shipment.setWarehouse(warehouse);
        shipmentService.create(shipment);
        return "redirect:/shipment";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        shipmentService.delete(id);
        return "redirect:/shipment";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("shipment") Shipment shipment) {
        shipmentService.update(shipment);
        return "redirect:/shipment";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getShipment(@PathVariable("id") Long id, Model model) {
        Shipment shipment = shipmentService.getById(id);
        model.addAttribute("shipment", shipment);
        return "redirect:/shipment";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editShipment(@ModelAttribute Shipment invoice) {
        shipmentService.update(invoice);
        return "redirect:/shipment";
    }

    @GetMapping("/remainder")
    public String showRemainingQuantities(Model model) {
        List<Object[]> remainder = shipmentRepository.getRemainingQuantities();
        model.addAttribute("remainder", remainder);
        return "remainder";
    }
}
