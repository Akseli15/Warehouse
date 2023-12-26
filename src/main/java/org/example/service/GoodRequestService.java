package org.example.service;

import org.example.entity.Good;
import org.example.entity.GoodRequest;
import org.example.entity.Invoice;
import org.example.entity.Store;
import org.example.repository.GoodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodRequestService {

    @Autowired
    private GoodRequestRepository goodRequestRepository;
    @Autowired
    GoodService goodService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    StoreService storeService;

    public List<GoodRequest> getAll() {
        return goodRequestRepository.findAll();
    }

    public GoodRequest getById(Long good_request_id) {
        return  goodRequestRepository.findById(good_request_id).orElse(null);
    }

    public void create(GoodRequest goodRequest) {
        goodRequestRepository.save(goodRequest);
    }
    public void delete(Long good_request_id) {
        goodRequestRepository.deleteById(good_request_id);
    }

    public void update(GoodRequest goodRequest) {
        GoodRequest goodRequest1 = getById(goodRequest.getId());
        Store store = storeService.getByStoreName(goodRequest.getStore().getName());
        Good good = goodService.getByGoodName(goodRequest.getGood().getName());
        Invoice invoice = invoiceService.getById(goodRequest.getInvoice().getId());
        goodRequest1.setQuantity(goodRequest.getQuantity());
        goodRequestRepository.save(goodRequest1);
    }
}
