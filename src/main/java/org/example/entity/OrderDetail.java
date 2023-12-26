package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "order_detail_id")
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_order_id", referencedColumnName = "order_id", foreignKey = @ForeignKey(name = "fk_order_id"))
    private Zakaz zakaz;

    @ManyToOne
    @JoinColumn(columnDefinition = "BIGSERIAL", name = "fk_good_id", referencedColumnName = "good_id", foreignKey = @ForeignKey(name = "fk_good_id"))
    private Good good;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderDetail() {
    }

    public OrderDetail(Zakaz zakaz, Good good, Integer quantity) {
        this.zakaz = zakaz;
        this.good = good;
        this.quantity = quantity;
    }

    public OrderDetail(Long id, Zakaz zakaz, Good good, Integer quantity) {
        this.id = id;
        this.zakaz = zakaz;
        this.good = good;
        this.quantity = quantity;
    }
}
