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
                .mvcMatchers("/certification*", "/certification/**").permitAll()
                .mvcMatchers("/user/register", "/user/login", "/user/logout").permitAll()
                .mvcMatchers("/user/admin").hasRole(Role.ADMIN.getDesc())
                .mvcMatchers("/auth/daily-reward", "/common/code").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .build();
    }
}
