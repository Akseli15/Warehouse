package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipment")
public class Shipment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "shipment_id")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_good_id", referencedColumnName = "good_id", foreignKey = @ForeignKey(name = "fk_good_id"))
    private Good good;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_warehouse_id", referencedColumnName = "warehouse_id", foreignKey = @ForeignKey(name = "fk_warehouse_id"))
    private Warehouse warehouse;

    @Column(name = "quantity")
    private Integer quantity;

    public Shipment() {
    }

    public Shipment(Good good, Warehouse warehouse, Integer quantity) {
        this.good = good;
        this.warehouse = warehouse;
        this.quantity = quantity;
    }

    public Shipment(Long id, Good good, Warehouse warehouse, Integer quantity) {
        this.id = id;
        this.good = good;
        this.warehouse = warehouse;
        this.quantity = quantity;
    }
}
