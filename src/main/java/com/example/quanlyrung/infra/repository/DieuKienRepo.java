package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.DieuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DieuKienRepo extends JpaRepository<DieuKien, Long> {
}
