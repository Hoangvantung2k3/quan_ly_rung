package com.example.quanlyrung.infra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rung")
public class Rung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ten rung")
    private String name;

    @Column(name = "Toa Do")
    private String toaDo;

    @Column(name = "Dien Tich")
    private String dienTich;

    @ManyToOne
    @JoinColumn(name = "nguon_goc_id")
    private NguonGoc nguonGoc;

    @ManyToOne
    @JoinColumn(name = "dieu_kien_id")
    private DieuKien dieuKien;

    @ManyToOne
    @JoinColumn(name = "loai_cay_id")
    private LoaiCay loaiCay;

    @ManyToOne
    @JoinColumn(name = "loai_muc_dich_id")
    private LoaiMucDich loaiMucDich;

    @ManyToOne
    @JoinColumn(name = "chu_id")
    private Chu chu;

    @ManyToOne
    @JoinColumn(name = "tru_luong_id")
    private TruLuong truLuong;

    @ManyToOne
    @JoinColumn(name = "dvhc")
    private DVHC dvhc;

}
