package com.example.quanlyrung.service;

import com.example.quanlyrung.infra.entity.Permission;
import com.example.quanlyrung.infra.entity.Role;
import com.example.quanlyrung.infra.entity.User;
import com.example.quanlyrung.infra.repository.PermissionRepo;
import com.example.quanlyrung.infra.repository.RoleRepo;
import com.example.quanlyrung.infra.repository.UserRepo;
import com.example.quanlyrung.security.config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private PasswordConfig passwordConfig;



    public Permission savePermission(Permission permission) {
        return permissionRepo.save(permission);
    }


    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public void addPermissionToRole(Permission permissionName, Role roleName) {
        Role role = roleRepo.findRoleByName(roleName.getName());
        Permission permission = permissionRepo.findPermissionByName(permissionName.getName());
        role.getPermissionList().add(permission);
        saveRole(role);
    }


    public void addRoleToUser(Role roleName, User userName) {
        User user = userRepo.findUserByUsername(userName.getUsername());
        Role role = roleRepo.findRoleByName(roleName.getName());
        user.setRole(role);
        saveUser(user);
    }

    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException{
        User user = userRepo.findUserByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepo.save(user);
        } else {
            throw new UsernameNotFoundException("Could not found user with the email " + email);
        }
    }

    public User getUserByResetPasswordToken(String token) {
        return userRepo.findUserByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordConfig.passwordEncoder().encode(newPassword));
        user.setResetPasswordToken(null);
        userRepo.save(user);
    }

    public User getUserByUserName(String username) {
        return userRepo.findUserByUsername(username);
    }


    public List<User> getAllUser() {
        return userRepo.findAll();
    }

}
