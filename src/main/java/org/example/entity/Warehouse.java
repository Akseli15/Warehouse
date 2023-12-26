package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class Warehouse {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "warehouse_id")
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.REMOVE)
    private List<Shipment> shipments;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.REMOVE)
    private List<WarehouseSupplies> warehouseSupplies;

    public Warehouse() {
    }

    public Warehouse(String name, String address, List<Shipment> shipments, List<WarehouseSupplies> warehouseSupplies) {
        this.name = name;
        this.address = address;
        this.shipments = shipments;
        this.warehouseSupplies = warehouseSupplies;
    }

    public Warehouse(Long id, String name, String address, List<Shipment> shipments, List<WarehouseSupplies> warehouseSupplies) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.shipments = shipments;
        this.warehouseSupplies = warehouseSupplies;
    }
}
