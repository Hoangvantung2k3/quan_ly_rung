package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.LoaiCay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoaiCayRepo extends JpaRepository<LoaiCay, Long> {

}
