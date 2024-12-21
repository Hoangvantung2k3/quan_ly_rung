package com.example.quanlyrung.infra.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Rung} entity
 */
@Data
public class RungDto implements Serializable {
    private final Long id;
    private final String name;
    private final String toaDo;
    private final String dienTich;
    private final Long nguonGocId;
    private final String nguonGocName;
    private final DieuKienDto dieuKien;
    private final Long loaiCayId;
    private final String loaiCayName;

    /**
     * A DTO for the {@link DieuKien} entity
     */
    @Data
    public static class DieuKienDto implements Serializable {
        private final Long id;
        private final String name;
    }
}