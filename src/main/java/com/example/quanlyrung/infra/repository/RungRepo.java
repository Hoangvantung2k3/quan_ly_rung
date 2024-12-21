package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.Rung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface RungRepo extends JpaRepository<Rung, Long> {
//    @Query(
//            value = "SELECT * FROM rung",
//            nativeQuery = true)
    public List<Rung> findAll();

//    @Query("SELECT r FROM Rung r WHERE r.name LIKE %?1%")
//    public List<Rung> findAll(String keyword);

    public List<Rung> findAllByNameContainingIgnoreCase(String keyword);
    public Rung getRungById(Long id);

    public void deleteRungById(Long id);

    public List<Rung> getRungsByLoaiCayId(Long id);
    public List<Rung> getRungsByTruLuongId(Long id);
    public List<Rung> getRungsByDieuKienId(Long id);
    public List<Rung> getRungsByNguonGocId(Long id);
}
