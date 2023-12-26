package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store")
public class Store {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "store_id")
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;


    @Column(name = "contact_info")
    private String contactInfo;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<OrderAssembly> orderAssemblies;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<GoodRequest> goodRequests;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<WarehouseSupplies> warehouseSupplies;

    public Store() {
    }

    public Store(String name, String address, String contactInfo, List<OrderAssembly> orderAssemblies, List<GoodRequest> goodRequests, List<WarehouseSupplies> warehouseSupplies) {
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.orderAssemblies = orderAssemblies;
        this.goodRequests = goodRequests;
        this.warehouseSupplies = warehouseSupplies;
    }

    public Store(Long id, String name, String address, String contactInfo, List<OrderAssembly> orderAssemblies, List<GoodRequest> goodRequests, List<WarehouseSupplies> warehouseSupplies) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.orderAssemblies = orderAssemblies;
        this.goodRequests = goodRequests;
        this.warehouseSupplies = warehouseSupplies;
    }
}
