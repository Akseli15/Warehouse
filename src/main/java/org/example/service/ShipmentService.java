package org.example.service;

import org.example.entity.*;
import org.example.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    GoodService goodService;
    @Autowired
    WarehouseService warehouseService;

    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public Shipment getById(Long shipment_id) {
        return  shipmentRepository.findById(shipment_id).orElse(null);
    }

    public void create(Shipment shipment) {
        shipmentRepository.save(shipment);
    }
    public void delete(Long shipment_id) {
        shipmentRepository.deleteById(shipment_id);
    }

    public void update(Shipment shipment) {
        Shipment shipment1 = getById(shipment.getId());
        Warehouse warehouse = warehouseService.getByWarehouseName(shipment.getWarehouse().getName());
        Good good = goodService.getByGoodName(shipment.getGood().getName());
        shipment1.setQuantity(shipment.getQuantity());
        shipmentRepository.save(shipment1);
    }
}
