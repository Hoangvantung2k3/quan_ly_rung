package com.example.quanlyrung.service;

import com.example.quanlyrung.infra.entity.*;
import com.example.quanlyrung.infra.predicate.ChuPredicate;
import com.example.quanlyrung.infra.repository.*;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class RungService {

    @Autowired
    private RungRepo rungRepo;

    @Autowired
    private NguonGocRepo nguonGocRepo;

    @Autowired
    private DieuKienRepo dieuKienRepo;

    @Autowired
    private LoaiCayRepo loaiCayRepo;

    @Autowired
    private LoaiMucDichRepo loaiMucDichRepo;

    @Autowired
    private ChuRepo chuRepo;

    @Autowired
    private TruLuongRepo truLuongRepo;

    @Autowired
    private DVHCRepo dvhcRepo;

    public List<Rung> getAllRungByKeyword(String keyword) {
        if (keyword != null) {
            return rungRepo.findAllByNameContainingIgnoreCase(keyword);
        }
        return rungRepo.findAll();
    }

    public Rung getRungById(Long id) {
        return rungRepo.getRungById(id);
    }


    public void deleteRungById(Long id) {
        rungRepo.deleteRungById(id);
    }

    public void save(Rung rung) {
        rungRepo.save(rung);
    }

    public List<Rung> getAllRungByLoaiCay(Long id) {
        return rungRepo.getRungsByLoaiCayId(id);
    }

    public List<Rung> getAllRungByTruLuong(Long id) {
        return rungRepo.getRungsByTruLuongId(id);
    }
    public List<Rung> getAllRungByDieuKien(Long id) {
        return rungRepo.getRungsByDieuKienId(id);
    }
    public List<Rung> getAllRungByNguonGoc(Long id) {
        return rungRepo.getRungsByNguonGocId(id);
    }

    public void setModel(Model model) {
        List<NguonGoc> nguonGocList = nguonGocRepo.findAll();
        List<DieuKien> dieuKienList = dieuKienRepo.findAll();
        List<LoaiCay> loaiCayList = loaiCayRepo.findAll();
        List<LoaiMucDich> loaiMucDichList = loaiMucDichRepo.findAll();
        List<Chu> chuList = chuRepo.findAll();
        List<TruLuong> truLuongList = truLuongRepo.findAll();
        List<DVHC> huyenList = dvhcRepo.findAllByLevelEqualsIgnoreCase("Huyện");
        List<DVHC> xaList = dvhcRepo.findAllByLevelEqualsIgnoreCase("Xã");

        model.addAttribute("nguonGocList", nguonGocList);
        model.addAttribute("dieuKienList", dieuKienList);
        model.addAttribute("loaiCayList", loaiCayList);
        model.addAttribute("loaiMucDichList", loaiMucDichList);
        model.addAttribute("chuList", chuList);
        model.addAttribute("truLuongList", truLuongList);
        model.addAttribute("huyenList", huyenList);
        model.addAttribute("xaList", xaList);
    }

    public Page<Rung> getAllRungPagination(int limit, int offset) {
        Page<Rung> page = rungRepo.findAll(PageRequest.of(offset, limit));
        return page;
    }

}
