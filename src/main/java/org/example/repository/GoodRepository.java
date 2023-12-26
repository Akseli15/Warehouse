package org.example.repository;

import org.example.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {

    @Query("SELECT g.name AS product_name FROM Good g " +
            "LEFT JOIN g.orderDetails od " +
            "LEFT JOIN od.zakaz o " +
            "WHERE FUNCTION('MONTH', o.orderDate) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND o.id IS NULL")
    List<Object[]> getProductOrderVolume();

    Good findFirstByName(String name);
}