package org.example.service;

import org.example.entity.Invoice;
import org.example.entity.Zakaz;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    InvoiceService invoiceService;

    public List<Zakaz> getAll() {
        return orderRepository.findAll();
    }

    public Zakaz getById(Long order_id) {
        return  orderRepository.findById(order_id).orElse(null);
    }

    public void create(Zakaz zakaz) {
        orderRepository.save(zakaz);
    }
    public void delete(Long order_id) {
        orderRepository.deleteById(order_id);
    }

    public void update(Zakaz zakaz) {
        Zakaz zakaz1 = getById(zakaz.getId());
        Invoice invoice = invoiceService.getById(zakaz.getInvoice().getId());
        zakaz1.setOrderDate(zakaz.getOrderDate());
        zakaz1.setStatus(zakaz.getStatus());
        zakaz1.setPrice(zakaz.getPrice());
        orderRepository.save(zakaz1);
    }
}
