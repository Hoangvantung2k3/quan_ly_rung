package com.example.quanlyrung.security.config;

import com.example.quanlyrung.security.auth.ApplicationUserDetails;
import com.example.quanlyrung.security.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {
    @Autowired
    private ApplicationUserService userService;

    @Autowired
    private PasswordConfig passwordConfig;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordConfig.passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/rungs/**")
                .hasAnyAuthority("ADMIN")
//                .hasAnyAuthority("CREATE", "READ", "DELETE", "UPDATE")
//                .requestMatchers("/user/registration/**").permitAll()
//                .requestMatchers("/forgot_password").permitAll()
//                .requestMatchers("/reset_password**").permitAll()
//                .anyRequest()
                .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true)
                .and()
                .rememberMe().rememberMeParameter("remember-me")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }
}
