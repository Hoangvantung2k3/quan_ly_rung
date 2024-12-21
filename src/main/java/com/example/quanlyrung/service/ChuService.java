package com.example.quanlyrung.service;

import com.example.quanlyrung.infra.entity.Chu;
import com.example.quanlyrung.infra.entity.DVHC;
import com.example.quanlyrung.infra.repository.ChuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuService {
    @Autowired
    private ChuRepo chuRepo;

    public List<Chu> getAllChu(String keyword) {
        if (keyword != null) {
            return chuRepo.findAllByNameContainingIgnoreCase(keyword);
        }
        return chuRepo.findAll();
    }

    public void save(Chu chu) {
        chuRepo.save(chu);
    }
    public Chu getChuById(Long id) {
        return chuRepo.getChuById(id);
    }

    public void deleteChuById(Long id) {
        chuRepo.deleteChuById(id);
    }
}
