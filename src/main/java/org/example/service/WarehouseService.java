package org.example.service;

import org.example.entity.Store;
import org.example.entity.Warehouse;
import org.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    public Warehouse getById(Long warehouse_id) {
        return  warehouseRepository.findById(warehouse_id).orElse(null);
    }

    public void create(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }
    public void delete(Long warehouse_id) {
        warehouseRepository.deleteById(warehouse_id);
    }

    public void update(Warehouse warehouse) {
        Warehouse warehouse1 = getById(warehouse.getId());
        warehouse1.setName(warehouse.getName());
        warehouse1.setAddress(warehouse.getAddress());
        warehouseRepository.save(warehouse1);
    }
    public Warehouse getByWarehouseName(String name) {
        return warehouseRepository.findFirstByName(name);
    }
}
