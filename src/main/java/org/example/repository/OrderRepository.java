package org.example.repository;

import org.example.entity.Zakaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Zakaz, Long> {

    @Query("SELECT g.name AS product_name, (SUM(og.quantity) - COALESCE(SUM(s.quantity), 0)) AS required_quantity FROM Zakaz o " +
            "JOIN OrderDetail og ON o.id = og.zakaz.id " +
            "JOIN Good g ON og.good.id = g.id " +
            "LEFT JOIN Shipment s ON g.id = s.good.id " +
            "WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE) + 1 " +
            "GROUP BY g.name")
    List<Object[]> getRequiredQuantitiesForNextMonth();
}
