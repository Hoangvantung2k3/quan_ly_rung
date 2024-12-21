package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Long> {

    public List<Role> findAll();
    public Role findRoleByName(String name);
}
