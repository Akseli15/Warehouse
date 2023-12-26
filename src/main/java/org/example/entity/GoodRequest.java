package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "good_request")
@Getter
@Setter
public class GoodRequest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "good_request_id")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_store_id", referencedColumnName = "store_id", foreignKey = @ForeignKey(name = "fk_store_id"))
    private Store store;

    @ManyToOne
    @JoinColumn(name = "fk_good_id", referencedColumnName = "good_id", foreignKey = @ForeignKey(name = "fk_good_id"))
    private Good good;

    @ManyToOne
    @JoinColumn(name = "fk_invoice_id", referencedColumnName = "invoice_id", foreignKey = @ForeignKey(name = "fk_invoice_id"))
    private Invoice invoice;

    @Column(name = "quantity")
    private Integer quantity;

    public GoodRequest() {
    }

    public GoodRequest(Store store, Good good, Invoice invoice, Integer quantity) {
        this.store = store;
        this.good = good;
        this.invoice = invoice;
        this.quantity = quantity;
    }

    public GoodRequest(Long id, Store store, Good good, Invoice invoice, Integer quantity) {
        this.id = id;
        this.store = store;
        this.good = good;
        this.invoice = invoice;
        this.quantity = quantity;
    }
}