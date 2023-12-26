package org.example.service;

import org.example.entity.*;
import org.example.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    GoodService goodService;

    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail getById(Long order_detail_id) {
        return  orderDetailRepository.findById(order_detail_id).orElse(null);
    }

    public void create(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
    public void delete(Long order_detail_id) {
        orderDetailRepository.deleteById(order_detail_id);
    }

    public void update(OrderDetail orderDetail) {
        OrderDetail orderDetail1 = getById(orderDetail.getId());
        Zakaz zakaz = orderService.getById(orderDetail.getZakaz().getId());
        Good good = goodService.getByGoodName(orderDetail.getGood().getName());
        orderDetail1.setZakaz(zakaz);
        orderDetail1.setGood(good);
        orderDetail1.setQuantity(orderDetail.getQuantity());
        orderDetailRepository.save(orderDetail1);
    }
}
