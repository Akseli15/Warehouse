package org.example.repository;

import org.example.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {

    @Query(value = "SELECT g.name AS product_name FROM good g " +
            "LEFT JOIN order_detail og ON g.good_id = og.fk_good_id " +
            "LEFT JOIN zakaz o ON og.fk_order_id = o.order_id AND EXTRACT(MONTH FROM o.order_date) = EXTRACT(MONTH FROM CURRENT_DATE) " +
            "WHERE o.order_id IS NULL", nativeQuery = true)
    List<Object[]> getProductOrderVolume();

    Good findFirstByName(String name);
}