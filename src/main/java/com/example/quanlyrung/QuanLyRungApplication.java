package com.example.quanlyrung;

import com.example.quanlyrung.infra.entity.Permission;
import com.example.quanlyrung.infra.entity.Role;
import com.example.quanlyrung.infra.entity.User;
import com.example.quanlyrung.infra.repository.PermissionRepo;
import com.example.quanlyrung.infra.repository.RoleRepo;
import com.example.quanlyrung.security.config.PasswordConfig;
import com.example.quanlyrung.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class QuanLyRungApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanLyRungApplication.class, args);
    }

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleRepo roleRepo;
//
//    @Autowired
//    private PermissionRepo permissionRepo;
//
//    @Autowired
//    PasswordConfig passwordConfig;
//
//    @Override
//    public void run (String[] args) throws Exception {
//        User admin = new User();
//        admin.setUsername("admin");
//        admin.setPassword(passwordConfig.passwordEncoder().encode("admin"));
//        userService.saveUser(admin);
//
//        Permission read = new Permission(null, "READ");
//        userService.savePermission(read);
//
//        Permission create = new Permission(null, "CREATE");
//        userService.savePermission(create);
//
//        Permission delete = new Permission(null, "DELETE");
//        userService.savePermission(delete);
//
//        Role roleAdmin = new Role(null, "ADMIN", new ArrayList<>());
//        userService.saveRole(roleAdmin);
//
//        userService.addPermissionToRole(read, roleAdmin);
//        userService.addPermissionToRole(create, roleAdmin);
//        userService.addPermissionToRole(delete, roleAdmin);
//        userService.addRoleToUser(roleAdmin, admin);
//    }

}
