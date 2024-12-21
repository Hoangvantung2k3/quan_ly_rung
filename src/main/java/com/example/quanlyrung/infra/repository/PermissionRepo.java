package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepo extends JpaRepository<Permission, Long> {
    public List<Permission> findAll();
    public Permission findPermissionByName(String name);
}
