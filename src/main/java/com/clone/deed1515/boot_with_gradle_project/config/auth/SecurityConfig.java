package com.clone.deed1515.boot_with_gradle_project.config.auth;

import com.clone.deed1515.boot_with_gradle_project.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
            .authorizeRequests( authorize -> authorize
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated())
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .oauth2Login(oauth2Login -> oauth2Login
                .userInfoEndpoint()
                .userService(customOAuth2UserService));
    }

}
