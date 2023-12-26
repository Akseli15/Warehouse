package org.example.repository;

import org.example.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Warehouse findFirstByName (String name);
}
