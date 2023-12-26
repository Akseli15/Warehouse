package org.example.service;

import org.example.entity.*;
import org.example.repository.WarehouseSuppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseSuppliesService {

    @Autowired
    private WarehouseSuppliesRepository warehouseSuppliesRepository;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    StoreService storeService;

    public List<WarehouseSupplies> getAll() {
        return warehouseSuppliesRepository.findAll();
    }

    public WarehouseSupplies getById(Long warehouse_supplies_id) {
        return  warehouseSuppliesRepository.findById(warehouse_supplies_id).orElse(null);
    }

    public void create(WarehouseSupplies warehouseSupplies) {
        warehouseSuppliesRepository.save(warehouseSupplies);
    }
    public void delete(Long warehouse_supplies_id) {
        warehouseSuppliesRepository.deleteById(warehouse_supplies_id);
    }

    public void update(WarehouseSupplies warehouseSupplies) {
        WarehouseSupplies warehouseSupplies1 = getById(warehouseSupplies.getId());
        Store store = storeService.getByStoreName(warehouseSupplies.getStore().getName());
        Warehouse warehouse = warehouseService.getByWarehouseName(warehouseSupplies.getWarehouse().getName());
        warehouseSuppliesRepository.save(warehouseSupplies1);
    }
}
