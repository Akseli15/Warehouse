package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL", name = "good_id")
    private Long id;

    @Column(columnDefinition = "TEXT", name = "name")
    private String name;

    @Column(columnDefinition = "TEXT", name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "good", cascade = CascadeType.REMOVE)
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "good", cascade = CascadeType.REMOVE)
    private List<GoodRequest> goodRequests;

    @OneToMany(mappedBy = "good", cascade = CascadeType.REMOVE)
    private List<Shipment> shipments;

    public Good() {
    }

    public Good(Long id, String name, String description, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Good(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}