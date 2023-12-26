package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_assembly")
public class OrderAssembly {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "assembly_id")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_store_id", referencedColumnName = "store_id", foreignKey = @ForeignKey(name = "fk_store_id"))
    private Store store;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_order_id", referencedColumnName = "order_id", foreignKey = @ForeignKey(name = "fk_order_id"))
    private Zakaz zakaz;

    public OrderAssembly() {
    }

    public OrderAssembly(Store store, Zakaz zakaz) {
        this.store = store;
        this.zakaz = zakaz;
    }

    public OrderAssembly(Long id, Store store, Zakaz zakaz) {
        this.id = id;
        this.store = store;
        this.zakaz = zakaz;
    }
}
