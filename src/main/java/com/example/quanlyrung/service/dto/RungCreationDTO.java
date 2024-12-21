package com.example.quanlyrung.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RungCreationDTO {
    private String name;
    private String toaDo;
    private String dienTich;
    private Long nguonGocId;
    private Long dieuKienId;
    private Long loaiCayId;
    private Long mucDichId;
    private Long loaiMucDichId;
    private Long loaiChuId;
    private Long chuId;
    private Long truLuongId;
    private Long huyenId;
    private Long xaId;
}
