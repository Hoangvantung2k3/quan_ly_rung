package com.example.quanlyrung.security.auth;

import com.example.quanlyrung.infra.entity.Role;
import com.example.quanlyrung.infra.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationUserDetails implements UserDetails {

    private List<GrantedAuthority> grantedAuthorities;
    private String password;
    private String userName;

    public ApplicationUserDetails(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        List<GrantedAuthority> authorities = user.getRole().getPermissionList()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
        authorities.add(0, new SimpleGrantedAuthority(user.getRole().getName()));
        this.grantedAuthorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
