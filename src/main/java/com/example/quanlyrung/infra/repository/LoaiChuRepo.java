package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.LoaiChu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiChuRepo extends JpaRepository<LoaiChu, Long> {
}
