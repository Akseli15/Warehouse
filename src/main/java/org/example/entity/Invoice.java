package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "invoice_id")
    @Id
    private Long id;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.REMOVE)
    private List<Zakaz> zakazs;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.REMOVE)
    private List<GoodRequest> goodRequest;

    public Invoice() {
    }

    public Invoice(LocalDate invoiceDate, List<Zakaz> zakazs) {
        this.invoiceDate = invoiceDate;
        this.zakazs = zakazs;
    }

    public Invoice(Long id, LocalDate invoiceDate, List<Zakaz> zakazs) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.zakazs = zakazs;
    }
}
