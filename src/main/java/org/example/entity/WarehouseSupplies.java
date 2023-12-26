package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "warehouse_supplies")
public class WarehouseSupplies {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "supplies_id")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_store_id", referencedColumnName = "store_id", foreignKey = @ForeignKey(name = "fk_store_id"))
    private Store store;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_warehouse_id", referencedColumnName = "warehouse_id", foreignKey = @ForeignKey(name = "fk_warehouse_id"))
    private Warehouse warehouse;

    public WarehouseSupplies() {
    }

    public WarehouseSupplies(Store store, Warehouse warehouse) {
        this.store = store;
        this.warehouse = warehouse;
    }

    public WarehouseSupplies(Long id, Store store, Warehouse warehouse) {
        this.id = id;
        this.store = store;
        this.warehouse = warehouse;
    }
}
