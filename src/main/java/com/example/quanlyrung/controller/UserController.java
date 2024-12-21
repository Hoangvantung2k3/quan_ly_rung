package com.example.quanlyrung.controller;

import com.example.quanlyrung.infra.entity.Role;
import com.example.quanlyrung.infra.entity.User;
import com.example.quanlyrung.infra.repository.RoleRepo;
import com.example.quanlyrung.security.auth.ApplicationUserDetails;
import com.example.quanlyrung.security.config.PasswordConfig;
import com.example.quanlyrung.service.RungService;
import com.example.quanlyrung.service.UserService;
import com.example.quanlyrung.service.dto.Mapper;
import com.example.quanlyrung.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private RungService rungService;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordConfig passwordConfig;


    @GetMapping("/registration")
    public String getRegistrationView(Model model) {
        User user = new User();
        List<Role> roleList = roleRepo.findAll();
        model.addAttribute("roleList", roleList);

        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration/save")
    public String saveUser(User user) {
        user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/get")
    public String getAllUser(Model model) {
        rungService.setModel(model);
        List<UserDTO> userDTOList = userService.getAllUser()
                .stream().map(u -> mapper.toUserDTO(u)).collect(Collectors.toList());
        model.addAttribute("userList", userDTOList);
        return "view-user-list";
    }

    @GetMapping("/update")
    public String updateUser(Model model, @AuthenticationPrincipal ApplicationUserDetails loggedUser) {
        rungService.setModel(model);
        String username = loggedUser.getUsername();
        User userUpdate = userService.getUserByUserName(username);
        model.addAttribute("userUpdate", userUpdate);

        List<Role> roleList = roleRepo.findAll();
        model.addAttribute("roleList", roleList);
        return "view-user-info";
    }

    @PostMapping ("/update/save")
    public String saveUpdatedUser(User user, @AuthenticationPrincipal ApplicationUserDetails loggedUser) {
        userService.saveUser(user);

        loggedUser.setUserName(user.getUsername());
        loggedUser.setPassword(user.getPassword());

        return "redirect:/user/get";
    }
}
