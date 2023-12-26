package org.example.service;

import org.example.entity.Good;
import org.example.entity.Store;
import org.example.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    public Store getById(Long store_id) {
        return  storeRepository.findById(store_id).orElse(null);
    }

    public void create(Store goodRequest) {
        storeRepository.save(goodRequest);
    }
    public void delete(Long store_id) {
        storeRepository.deleteById(store_id);
    }

    public void update(Store store) {
        Store store1 = getById(store.getId());
        store1.setName(store.getName());
        store1.setAddress(store.getAddress());
        store1.setContactInfo(store.getContactInfo());
        storeRepository.save(store1);
    }

    public Store getByStoreName(String name) {
        return storeRepository.findFirstByName(name);
    }
}
