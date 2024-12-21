package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findUserByUsername(String username);

    public void existsUserByUsername(String username);

    public User findUserByEmail(String email);

    public User findUserByResetPasswordToken(String token);

    public User findUserById(Long id);
}
