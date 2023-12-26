package org.example.repository;

import org.example.entity.OrderAssembly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAssemblyRepository  extends JpaRepository<OrderAssembly, Long> {
}
