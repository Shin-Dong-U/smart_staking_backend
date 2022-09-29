package com.yoyohub.staking.config;

import com.yoyohub.staking.common.code.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.antMatcher("/**")
                .authorizeRequests()
                .mvcMatchers("/certification/**").permitAll()
                .mvcMatchers("/user/register").permitAll()
                .mvcMatchers("/user/login").permitAll()
                .mvcMatchers("/user/logout").permitAll()
                .mvcMatchers("/user/admin").hasRole(Role.ADMIN.getDesc())
                .mvcMatchers("/auth/daily-reward").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .build();
    }
}
