package com.example.quanlyrung.service;

import com.example.quanlyrung.infra.entity.DVHC;
import com.example.quanlyrung.infra.repository.DVHCRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DVHCService {
    @Autowired
    private DVHCRepo dvhcRepo;

    public List<DVHC> getAllDVHCByLevel(String keyword, String level, Long parentId) {
        if (keyword != null) {
            return dvhcRepo.findAllByNameContainingIgnoreCaseAndLevelAndParentId(keyword, level, parentId);
        }
        return dvhcRepo.findAllByLevelEqualsIgnoreCaseAndParentId(level, parentId);
    }

    public void save(DVHC dvhc) {
        dvhcRepo.save(dvhc);
    }
    public DVHC getDVHCById(Long id) {
        return dvhcRepo.getDVHCById(id);
    }

    public void deleteDVHCById(Long id) {
        dvhcRepo.deleteDVHCById(id);
    }
    public List<DVHC> getAllXaByHuyenId(Long id) {
        return dvhcRepo.getDVHCSByParentId(id);
    }
}
