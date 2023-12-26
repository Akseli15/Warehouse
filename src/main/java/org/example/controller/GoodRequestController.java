package org.example.controller;

import org.example.entity.Good;
import org.example.entity.GoodRequest;
import org.example.entity.Invoice;
import org.example.entity.Store;
import org.example.service.GoodRequestService;
import org.example.service.GoodService;
import org.example.service.InvoiceService;
import org.example.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/good_request")
public class GoodRequestController {

    @Autowired
    GoodRequestService goodRequestService;
    @Autowired
    StoreService storeService;
    @Autowired
    GoodService goodService;
    @Autowired
    InvoiceService invoiceService;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("goodRequests", goodRequestService.getAll());
        model.addAttribute("stores", storeService.getAll());
        model.addAttribute("goods",goodService.getAll());
        model.addAttribute("invoices", invoiceService.getAll());
        return "good_request";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        GoodRequest goodRequest = goodRequestService.getById(id);
        model.addAttribute("goodRequest", goodRequest);
        return "good-request-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute GoodRequest goodRequest,
                         @RequestParam("storeName") String storeName,
                         @RequestParam("goodName") String goodName,
                         @RequestParam("invoiceId") Long invoiceId){
        Store store = storeService.getByStoreName(storeName);
        Good good = goodService.getByGoodName(goodName);
        Invoice invoice = invoiceService.getById(invoiceId);
        goodRequest.setStore(store);
        goodRequest.setGood(good);
        goodRequest.setInvoice(invoice);
        goodRequestService.create(goodRequest);
        return "redirect:/good_request";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        goodRequestService.delete(id);
        return "redirect:/good_request";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("goodRequest") GoodRequest goodRequest) {
        goodRequestService.update(goodRequest);
        return "redirect:/good_request";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getGoodRequest(@PathVariable("id") Long id, Model model) {
        GoodRequest goodRequest = goodRequestService.getById(id);
        model.addAttribute("goodRequest", goodRequest);
        return "redirect:/good_request";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editGoodRequest(@ModelAttribute GoodRequest goodRequest) {
        goodRequestService.update(goodRequest);
        return "redirect:/good_request";
    }
}
