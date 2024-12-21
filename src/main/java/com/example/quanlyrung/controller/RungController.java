package com.example.quanlyrung.controller;

import com.example.quanlyrung.infra.entity.*;
import com.example.quanlyrung.infra.repository.*;
import com.example.quanlyrung.service.RungService;
import com.example.quanlyrung.service.dto.Mapper;
import com.example.quanlyrung.service.dto.RungDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Transactional
@RequestMapping(path = "/rungs")
public class RungController {

    @Autowired
    private RungService rungService;

    @Autowired
    private LoaiCayRepo loaiCayRepo;

    @Autowired
    private TruLuongRepo truLuongRepo;

    @Autowired
    private DieuKienRepo dieuKienRepo;

    @Autowired
    private NguonGocRepo nguonGocRepo;

    @Autowired
    private DVHCRepo dvhcRepo;

    @Autowired
    private Mapper mapper;

    @GetMapping("/get")
    public String getAllRung(Model model, @Param("keyword") String keyword) {
        rungService.setModel(model);
        List<Rung> list = rungService.getAllRungByKeyword(keyword);
        List<RungDTO> dtoList =  list.stream().map(rung -> mapper.toDTO(rung)).collect(Collectors.toList());
        model.addAttribute("rung", dtoList);
        model.addAttribute("keyword", keyword);
        return "view-rung";
    }

    @GetMapping("/form")
    public String createNewRung(Model model) {
        rungService.setModel(model);
        Rung rung = new Rung();
        model.addAttribute("rung", rung);
        return "form-rung";
    }


    @GetMapping("/save")
    public String saveNewRung(Rung rung) {
        rungService.save(rung);
        return "redirect:/rungs/get";
    }

    @GetMapping("/delete/{id}")
    public String deleteRung(@PathVariable(name = "id") Long id) {
        rungService.deleteRungById(id);
        return "redirect:/rungs/get";
    }


    @GetMapping("/edit/{id}")
    public String editRung(@PathVariable(name = "id") Long id, Model model) {
        rungService.setModel(model);
        Rung rung = rungService.getRungById(id);
        model.addAttribute("rung", rung);
        return "form-rung";
    }


    @GetMapping("/loaicay/")
    public String searchRungInLoaiCay(Model model, @Param("keyword") String keyword) {
        searchRungByName(model, keyword);
        List<LoaiCay> loaiCayList = loaiCayRepo.findAll();
        model.addAttribute("loaiCayList", loaiCayList);
        return "view-rung-loaicay";
    }

    @GetMapping("/loaicay/{id}")
    public String getRungsByLoaiCay(Model model, @PathVariable("id") Long id) {
        List<Rung> list = rungService.getAllRungByLoaiCay(id);
        List<LoaiCay> loaiCayList = loaiCayRepo.findAll();

        model.addAttribute("rung", list);
        model.addAttribute("loaiCayList", loaiCayList);

        return "view-rung-loaicay";
    }
    @GetMapping("/truluong/")
    public String searchRungInTruLuong(Model model, @Param("keyword") String keyword) {
        searchRungByName(model, keyword);
        List<TruLuong> truLuongList = truLuongRepo.findAll();
        model.addAttribute("truLuongList", truLuongList);
        return "view-rung-truluong";
    }
    @GetMapping("/truluong/{id}")
    public String getRungsByTruLuong(Model model, @PathVariable("id") Long id) {
        List<TruLuong> truLuongList = truLuongRepo.findAll();
        model.addAttribute("truLuongList", truLuongList);

        List<Rung> list = rungService.getAllRungByTruLuong(id);
        model.addAttribute("rung", list);
        return "view-rung-truluong";
    }

    @GetMapping("/dieukien/")
    public String searchRungInDieuKien(Model model, @Param("keyword") String keyword) {
        searchRungByName(model, keyword);
        List<DieuKien> dieuKienList = dieuKienRepo.findAll();
        model.addAttribute("dieuKienList", dieuKienList) ;
        return "view-rung-dieukien";
    }
    @GetMapping("/dieukien/{id}")
    public String getRungsByDieuKien(Model model, @PathVariable("id") Long id) {
        List<DieuKien> dieuKienList = dieuKienRepo.findAll();
        model.addAttribute("dieuKienList", dieuKienList) ;

        List<Rung> list = rungService.getAllRungByDieuKien(id);
        model.addAttribute("rung", list);
        return "view-rung-dieukien";
    }

    @GetMapping("/nguongoc/")
    public String searchRungInNguonGoc(Model model, @Param("keyword") String keyword) {
        searchRungByName(model, keyword);
        List<NguonGoc> nguonGocList = nguonGocRepo.findAll();
        model.addAttribute("nguonGocList", nguonGocList) ;
        return "view-rung-nguongoc";
    }
    @GetMapping("/nguongoc/{id}")
    public String getRungsByNguonGoc(Model model, @PathVariable("id") Long id) {
        List<NguonGoc> nguonGocList = nguonGocRepo.findAll();
        model.addAttribute("nguonGocList", nguonGocList) ;

        List<Rung> list = rungService.getAllRungByNguonGoc(id);
        model.addAttribute("rung", list);
        return "view-rung-nguongoc";
    }
    public void searchRungByName(Model model, String keyword) {
        List<Rung> list = rungService.getAllRungByKeyword(keyword);
        model.addAttribute("rung", list);
        model.addAttribute("keyword", keyword);
    }

    @GetMapping("/get/{rungId}")
    public RungDTO getRungById(@PathVariable(name = "rungId") Long id) {
        RungDTO rungDTO = mapper.toDTO(rungService.getRungById(id));
        return rungDTO;
    }
}
