package com.example.quanlyrung.service.dto;

import com.example.quanlyrung.infra.entity.NguonGoc;
import com.example.quanlyrung.infra.entity.Rung;
import com.example.quanlyrung.infra.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    public RungDTO toDTO(Rung rung) {
        Long id = rung.getId();
        String name = rung.getName();
        String toaDo = rung.getToaDo();
        String dienTich = rung.getToaDo();
        String nguonGoc = rung.getNguonGoc().getName();
        String dieuKien = rung.getDieuKien().getName();
        String loaiCay = rung.getLoaiCay().getName();
        String loaiMucDich = rung.getLoaiMucDich().getName();
        String mucDich = rung.getLoaiMucDich().getMucDich().getName();
        String chu = rung.getChu().getName();
        String loaiChu = rung.getChu().getLoaiChu().getName();
        String truLuong = rung.getTruLuong().getName();
        String xa = rung.getDvhc().getName();
        String huyen = rung.getDvhc().getParent().getName();

        RungDTO rungDTO = new RungDTO(id, name, toaDo, dienTich, nguonGoc, dieuKien, loaiCay, loaiMucDich, mucDich, chu, loaiChu, truLuong, xa, huyen);
        return rungDTO;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole().getName());
        userDTO.setPermission(user.getRole().getPermissionList()
                .stream().map(p->p.getName()).collect(Collectors.toList()));
        return userDTO;
    }
//    public RungDAO toRung(RungCreationDTO rungCreationDTO) {
//        Rung rung = new Rung();
//        rung.setName(rungCreationDTO.getName());
//        rung.setToaDo(rungCreationDTO.getToaDo());
//        rung.setDienTich(rungCreationDTO.getDienTich());
//        rung.setNguonGoc(new NguonGoc(rungCreationDTO.getNguonGocId()));
//    }
}
