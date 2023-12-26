package org.example.repository;

import org.example.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Query("SELECT g.name AS product_name, COALESCE(SUM(s.quantity), 0) AS remaining_quantity FROM Good g " +
            "LEFT JOIN Shipment s ON g.id = s.good.id " +
            "GROUP BY g.name")
    List<Object[]> getRemainingQuantities();
}
