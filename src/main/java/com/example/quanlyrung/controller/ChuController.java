package com.example.quanlyrung.controller;

import com.example.quanlyrung.infra.entity.Chu;
import com.example.quanlyrung.infra.entity.LoaiChu;
import com.example.quanlyrung.infra.repository.LoaiChuRepo;
import com.example.quanlyrung.service.ChuService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chu")
@Transactional
public class ChuController {
    @Autowired
    private ChuService chuService;

    @Autowired
    private LoaiChuRepo loaiChuRepo;

    @GetMapping("/get")
    public String getAllChu(Model model, @Param("keyword") String keyword) {
        List<Chu> chuList = chuService.getAllChu(keyword);
        model.addAttribute("chuList", chuList);
        model.addAttribute("keyword", keyword);
        return "view-chu";
    }

    @GetMapping("/create")
    public String createNewChu(Model model) {
        Chu chu = new Chu();
        model.addAttribute("chu", chu);

        List<LoaiChu> loaiChuList = loaiChuRepo.findAll();
        model.addAttribute("loaiChuList", loaiChuList);
        return "form-chu";
    }
    @GetMapping("/save")
    public String saveNewChu(Chu chu) {
        chuService.save(chu);
        return "redirect:/chu/get";
    }
    @GetMapping("/edit/{id}")
    public String editChu(Model model, @PathVariable("id") Long id) {
        Chu chu = chuService.getChuById(id);
        model.addAttribute("chu", chu);
        List<LoaiChu> loaiChuList = loaiChuRepo.findAll();
        model.addAttribute("loaiChuList", loaiChuList);
        return "form-chu";
    }

    @GetMapping("/delete/{id}")
    public String deleteChuById(@PathVariable(name = "id") Long id) {
       chuService.deleteChuById(id);
        return "redirect:/chu/get";
    }

}
