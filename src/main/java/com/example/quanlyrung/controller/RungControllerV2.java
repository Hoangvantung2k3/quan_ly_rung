package com.example.quanlyrung.controller;

import com.example.quanlyrung.infra.entity.Rung;
import com.example.quanlyrung.service.RungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/rungs")
public class RungControllerV2 {
    @Autowired
    private RungService rungService;

    @GetMapping("")
    public Page<Rung> getAllRung(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset) {
        return rungService.getAllRungPagination(limit, offset);
    }

}
