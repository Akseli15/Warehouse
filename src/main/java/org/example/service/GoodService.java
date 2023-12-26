package org.example.service;

import org.example.entity.Good;
import org.example.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;

    public List<Good> getAll() {
        return goodRepository.findAll();
    }

    public Good getById(Long good_id) {
        return  goodRepository.findById(good_id).orElse(null);
    }

    public void create(Good good) {
        goodRepository.save(good);
    }
    public void delete(Long good_id) {
        goodRepository.deleteById(good_id);
    }

    public void update(Good good) {
        Good good1 = getById(good.getId());
        good1.setName(good.getName());
        good1.setDescription(good.getDescription());
        good1.setPrice(good.getPrice());
        goodRepository.save(good1);
    }
    public Good getByGoodName(String name) {
        return goodRepository.findFirstByName(name);
    }
}