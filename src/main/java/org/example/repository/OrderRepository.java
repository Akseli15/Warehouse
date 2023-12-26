package org.example.repository;

import org.example.entity.Zakaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Zakaz, Long> {

    @Query(value = "SELECT " +
            "    g.name AS product_name, " +
            "    COALESCE(SUM(o.quantity), 0) AS order_volume, " +
            "    COALESCE(SUM(s.quantity), 0) AS remaining_quantity, " +
            "    CASE " +
            "        WHEN COALESCE(SUM(o.quantity), 0) > COALESCE(SUM(s.quantity), 0) " +
            "THEN COALESCE(SUM(o.quantity), 0) - COALESCE(SUM(s.quantity), 0) " +
            "        ELSE 0 " +
            "    END AS quantity_to_order " +
            "FROM good g " +
            "LEFT JOIN order_detail o ON g.good_id = o.fk_good_id " +
            "LEFT JOIN shipment s ON g.good_id = s.fk_good_id " +
            "LEFT JOIN zakaz ord ON o.fk_order_id = ord.order_id AND " +
            "EXTRACT(MONTH FROM ord.order_date) = EXTRACT(MONTH FROM CURRENT_DATE + INTERVAL '1 month') " +
            "WHERE EXTRACT(MONTH FROM COALESCE(ord.order_date, CURRENT_DATE)) = EXTRACT(MONTH FROM CURRENT_DATE) " +
            "GROUP BY g.name", nativeQuery = true)
    List<Object[]> getRequiredQuantitiesForNextMonth();

    @Query(value = "SELECT g.name AS product_name, COALESCE(SUM(og.quantity), 0) AS order_volume " +
            "FROM good g " +
            "LEFT JOIN order_detail og ON g.good_id = og.fk_good_id " +
            "LEFT JOIN zakaz o ON og.fk_order_id = o.order_id AND EXTRACT(MONTH FROM o.order_date) = EXTRACT(MONTH FROM CURRENT_DATE) " +
            "WHERE EXTRACT(MONTH FROM COALESCE(o.order_date, CURRENT_DATE)) = EXTRACT(MONTH FROM CURRENT_DATE) " +
            "GROUP BY g.name", nativeQuery = true)
    List<Object[]> getProductOrderVolume();

    @Query(value = "SELECT g.name AS product_name, od.quantity AS order_quantity FROM order_detail od " +
            "JOIN good g ON od.fk_good_id = g.good_id " +
            "WHERE od.fk_order_id = :orderId", nativeQuery = true)
    List<Object[]> getCurrentOrderDetail(@Param("orderId") Long orderId);
}
