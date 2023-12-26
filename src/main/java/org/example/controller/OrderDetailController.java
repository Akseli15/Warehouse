package org.example.controller;

import org.example.entity.Good;
import org.example.entity.Zakaz;
import org.example.service.GoodService;
import org.example.service.OrderDetailService;
import org.example.entity.OrderDetail;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order_detail")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderService orderService;
    @Autowired
    GoodService goodService;

    @Async
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("orderDetails", orderDetailService.getAll());
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("goods",goodService.getAll());
        return "order_detail";
    }
    @Async
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        OrderDetail orderDetail = orderDetailService.getById(id);
        model.addAttribute("orderDetail", orderDetail);
        return "order-detail-details";
    }
    @Async
    @PostMapping("/create")
    public String create(@ModelAttribute OrderDetail orderDetail,
                         @RequestParam ("orderId") Long orderId,
                         @RequestParam("goodName") String goodName){
        Zakaz zakaz = orderService.getById(orderId);
        Good good = goodService.getByGoodName(goodName);
        orderDetail.setZakaz(zakaz);
        orderDetail.setGood(good);
        orderDetailService.create(orderDetail);
        return "redirect:/order_detail";
    }
    @Async
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderDetailService.delete(id);
        return "redirect:/order_detail";
    }
    @Async
    @PostMapping("/save")
    public String save(@ModelAttribute("orderDetail") OrderDetail orderDetail) {
        orderDetailService.update(orderDetail);
        return "redirect:/order_detail";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getOrderDetail(@PathVariable("id") Long id, Model model) {
        OrderDetail orderDetail = orderDetailService.getById(id);
        model.addAttribute("orderDetail", orderDetail);
        return "redirect:/order_detail";
    }
    @Async
    @PostMapping("/edit/{id}")
    public String editOrderDetail(@ModelAttribute OrderDetail orderDetail) {
        orderDetailService.update(orderDetail);
        return "redirect:/order_detail";
    }
}
