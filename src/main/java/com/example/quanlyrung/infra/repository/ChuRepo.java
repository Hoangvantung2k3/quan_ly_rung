package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.Chu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface ChuRepo extends JpaRepository<Chu, Long>, QuerydslPredicateExecutor<Chu> {
    List<Chu> findAllByNameContainingIgnoreCase(String keyword);

    public Chu getChuById(Long id);

    public void deleteChuById(Long id);
}
