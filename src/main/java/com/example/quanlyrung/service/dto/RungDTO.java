package com.example.quanlyrung.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RungDTO {
    private Long id;
    private String name;
    private String toaDo;
    private String dienTich;
    private String nguonGoc;
    private String dieuKien;
    private String loaiCay;
    private String loaiMucDich;
    private String mucDich;
    private String chu;
    private String loaiChu;
    private String truLuong;
    private String xa;
    private String huyen;

    public String getName() {
        return name;
    }
}
