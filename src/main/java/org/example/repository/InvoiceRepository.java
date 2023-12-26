package org.example.repository;

import org.example.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
