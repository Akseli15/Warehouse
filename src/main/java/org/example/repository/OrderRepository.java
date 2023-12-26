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

    @Query("SELECT g.name AS product_name, COALESCE(SUM(og.quantity), 0) AS order_volume " +
            "FROM Good g " +
            "LEFT JOIN OrderDetail og ON g.id = og.good.id " +
            "LEFT JOIN Zakaz z ON og.zakaz.id = z.id " +
            "WHERE MONTH(COALESCE(z.orderDate, CURRENT_DATE)) = MONTH(CURRENT_DATE) " +
            "GROUP BY g.name")
    List<Object[]> getProductOrderVolume();
}
