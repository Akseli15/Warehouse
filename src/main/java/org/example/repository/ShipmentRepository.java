package org.example.repository;

import org.example.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Query(value= "SELECT g.name AS product_name, COALESCE(SUM(s.quantity), 0) AS remaining_quantity " +
            "FROM good g " +
            "LEFT JOIN shipment s ON g.good_id = s.fk_good_id " +
            "GROUP BY g.name", nativeQuery = true)
    List<Object[]> getRemainingQuantities();
}
