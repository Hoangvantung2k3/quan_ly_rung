package com.example.quanlyrung.controller;

import com.example.quanlyrung.infra.entity.DVHC;
import com.example.quanlyrung.service.DVHCService;
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
@RequestMapping("/dvhc")
@Transactional
public class DVHCController {
    @Autowired
    private DVHCService dvhcService;

    @GetMapping("/huyen")
    public String getAllDVHC(Model model,@Param("keyword") String keyword) {
        List<DVHC> huyenList = dvhcService.getAllDVHCByLevel(keyword, "Huyện", null);
        model.addAttribute("huyenList", huyenList);
        model.addAttribute("keyword", keyword);
        return "view-dvhc-huyen";
    }

    @GetMapping("/huyen/{id}")
    public String getHuyenById(Model model, @PathVariable("id") Long id, @Param("keyword") String keyword) {
        DVHC huyen = dvhcService.getDVHCById(id);
        List<DVHC> xaList = dvhcService.getAllDVHCByLevel(keyword, "Xã", id);
        model.addAttribute("huyen", huyen);
        model.addAttribute("xaList", xaList);
        model.addAttribute("keyword", keyword);
        return "view-dvhc-xa";
    }

    @GetMapping("/xa/create")
    public String getCreateXaView(Model model) {
        DVHC xa = new DVHC();
        model.addAttribute("xa", xa);

        List<DVHC> huyenList = dvhcService.getAllDVHCByLevel(null, "Huyện", null);
        model.addAttribute("huyenList", huyenList);

        return "form-dvhc-xa";
    }

    @GetMapping("/xa/save/")
    public String saveNewXa(DVHC dvhc) {
        dvhcService.save(dvhc);
        return "redirect:/dvhc/huyen/" + dvhc.getParent().getId();
    }

    @GetMapping("/xa/edit/{id}")
    public String getEditXaView(@PathVariable(name = "id") Long id, Model model) {
        DVHC xa = dvhcService.getDVHCById(id);
        model.addAttribute("xa", xa);

        List<DVHC> huyenList = dvhcService.getAllDVHCByLevel(null, "Huyện", null);
        model.addAttribute("huyenList", huyenList);
        return "form-dvhc-xa";
    }
    @GetMapping("/huyen/create")
    public String getCreateDVHCView(Model model) {
        DVHC huyen = new DVHC();
        model.addAttribute("huyen", huyen);
        return "form-dvhc-huyen";
    }

    @GetMapping("/huyen/edit/{id}")
    public String getEditHuyenView(@PathVariable(name = "id") Long id, Model model) {
        DVHC huyen = dvhcService.getDVHCById(id);
        model.addAttribute("huyen", huyen);
        return "form-dvhc-huyen";
    }
    @GetMapping("/huyen/save")
    public String saveNewHuyen(DVHC dvhc) {
        dvhcService.save(dvhc);
        return "redirect:/dvhc/huyen";
    }

    @GetMapping("/delete/{id}")
    public String deleteHuyenById(@PathVariable(name = "id") Long id) {
        dvhcService.deleteDVHCById(id);
        return "redirect:/dvhc/huyen";
    }
}
