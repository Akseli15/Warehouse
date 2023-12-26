package org.example.service;

import org.example.entity.Invoice;
import org.example.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    public Invoice getById(Long invoice_id) {
        return  invoiceRepository.findById(invoice_id).orElse(null);
    }

    public void create(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
    public void delete(Long invoice_id) {
        invoiceRepository.deleteById(invoice_id);
    }

    public void update(Invoice invoice) {
        Invoice invoice1 = getById(invoice.getId());
        invoice1.setInvoiceDate(invoice.getInvoiceDate());
        invoiceRepository.save(invoice1);
    }
}
