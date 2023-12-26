package org.example.service;

import org.example.entity.OrderAssembly;
import org.example.entity.Store;
import org.example.entity.Zakaz;
import org.example.repository.OrderAssemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAssemblyService {

    @Autowired
    private OrderAssemblyRepository orderAssemblyRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    StoreService storeService;

    public List<OrderAssembly> getAll() {
        return orderAssemblyRepository.findAll();
    }

    public OrderAssembly getById(Long order_assembly_id) {
        return  orderAssemblyRepository.findById(order_assembly_id).orElse(null);
    }

    public void create(OrderAssembly goodRequest) {
        orderAssemblyRepository.save(goodRequest);
    }
    public void delete(Long order_assembly_id) {
        orderAssemblyRepository.deleteById(order_assembly_id);
    }

    public void update(OrderAssembly orderAssembly) {
        OrderAssembly orderAssembly1 = getById(orderAssembly.getId());
        Store store = storeService.getByStoreName(orderAssembly.getStore().getName());
        Zakaz zakaz = orderService.getById(orderAssembly.getZakaz().getId());
        orderAssemblyRepository.save(orderAssembly1);
    }
}
