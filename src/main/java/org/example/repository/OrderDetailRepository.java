package org.example.repository;

import org.example.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT g.name AS product_name, COALESCE(SUM(og.quantity), 0) AS order_volume FROM Good g " +
            "LEFT JOIN OrderDetail og ON g.id = og.good.id " +
            "LEFT JOIN Zakaz o ON og.zakaz.id = o.id AND MONTH(o.orderDate) = MONTH(CURRENT_DATE) " +
            "WHERE MONTH(COALESCE(o.orderDate, CURRENT_DATE)) = MONTH(CURRENT_DATE) " +
            "GROUP BY g.name")
    List<Object[]> getOrderVolumesForCurrentMonth();

    // Вот это запрос нужно исправить, либо дополнить
    @Query("SELECT g.name AS product_name, od.quantity AS order_quantity FROM OrderDetail od " +
            "JOIN Good g ON od.good.id = g.id " +
            "WHERE od.zakaz.id = :orderId")
    List<Object[]> getGoodsAndQuantitiesInOrder(Long orderId);
}
