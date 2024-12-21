package com.example.quanlyrung.controller;

import com.example.quanlyrung.infra.entity.DVHC;
import com.example.quanlyrung.infra.entity.Rung;
import com.example.quanlyrung.service.DVHCService;
import com.example.quanlyrung.service.RungService;
import com.example.quanlyrung.service.dto.Mapper;
import com.example.quanlyrung.service.dto.RungDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class ExcelController {

    @Autowired
    private RungService rungService;
    @Autowired
    private DVHCService dvhcService;

    @Autowired
    private Mapper mapper;

    @GetMapping(value = "/export")
    public void exportToExcel(HttpServletResponse response) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        response.setHeader("Content-Disposition", "attachment; filename=Rung.xlsx");

        List<RungDTO> list = rungService.getAllRungByKeyword(null).stream().map(rung -> mapper.toDTO(rung)).collect(Collectors.toList());
        ExcelExporter<RungDTO> excelExporter = new ExcelExporter(list, "Rung");
        excelExporter.export(response);
    }
    @GetMapping(value = "/export2")
    public void exportToExcel2(HttpServletResponse response) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        response.setHeader("Content-Disposition", "attachment; filename=DVHC.xlsx");

        List<DVHC> list = dvhcService.getAllDVHCByLevel(null, "Huyen", null);
        ExcelExporter excelExporter = new ExcelExporter(list, "DVHC Huyen");
        excelExporter.export(response);
    }
}
