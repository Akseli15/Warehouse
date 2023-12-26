package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "zakaz")
public class Zakaz {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "order_id")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_invoice_id", referencedColumnName = "invoice_id", foreignKey = @ForeignKey(name = "fk_invoice_id"))
    private Invoice invoice;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(columnDefinition = "TEXT", name = "status")
    private String status;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "zakaz", cascade = CascadeType.REMOVE)
    private List<OrderAssembly> orderAssemblies;

    @OneToMany(mappedBy = "zakaz", cascade = CascadeType.REMOVE)
    private List<OrderDetail> orderDetails;

    public Zakaz() {
    }

    public Zakaz(Invoice invoice, LocalDate orderDate, String status, Integer price) {
        this.invoice = invoice;
        this.orderDate = orderDate;
        this.status = status;
        this.price = price;
    }

    public Zakaz(Long id, Invoice invoice, LocalDate orderDate, String status, Integer price) {
        this.id = id;
        this.invoice = invoice;
        this.orderDate = orderDate;
        this.status = status;
        this.price = price;
    }
}
