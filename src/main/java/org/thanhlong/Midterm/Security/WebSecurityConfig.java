package org.thanhlong.Midterm.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thanhlong.Midterm.Service.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public UserDetailsService customUserDetailsService() {
        return new UserDetailServiceImpl();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/Admin/**" , "/detailProduct/**" , "/editBrand/**", "/deleteBrand/**", "/updateProduct/**" ,"/editCategory/**","/deleteCategory/**" , "/editColor/**" , "/deleteColor/**").hasRole("ADMIN")
                        .requestMatchers("/Cart" , "/orderPage").authenticated()
                        .anyRequest().permitAll()
                )
                .authenticationProvider(authenticationProvider())
                .formLogin((form) -> form
                        .loginPage("/Login")
                        .loginProcessingUrl("/PerformLogin")
                        .defaultSuccessUrl("/HomePage", true) // Chuyển hướng sau khi đăng nhập thành công
                        .failureUrl("/Login") // Giữ người dùng ở trang đăng nhập sau khi đăng nhập thất bại
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling(
                        exception ->{
                            exception.accessDeniedPage("/403");
                        }
                );

        return http.build();
    }

}