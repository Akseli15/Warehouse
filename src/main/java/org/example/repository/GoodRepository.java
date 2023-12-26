package org.example.repository;

import org.example.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {

    @Query(value = "SELECT g.name AS product_name FROM Good g " +
            "LEFT JOIN OrderDetail og ON g.id = og.good.id " +
            "LEFT JOIN Order o ON og.order.id = o.id " +
            "WHERE o.id IS NULL AND EXTRACT(MONTH FROM o.orderDate) = EXTRACT(MONTH FROM CURRENT_DATE)",
            nativeQuery = true)
    List<String> findUnorderedProducts();
    Good findFirstByName(String name);
}