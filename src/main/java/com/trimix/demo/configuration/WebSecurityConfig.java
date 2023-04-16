package com.trimix.demo.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Value("${app.username}")
        private String username;
        @Value("${app.password}")
        private String password;


        @Bean
        public PasswordEncoder getPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            User userAdmin = new User(username,
                getPasswordEncoder().encode(password),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
            auth.inMemoryAuthentication().withUser(userAdmin);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .antMatchers("/personas").hasRole("ADMIN")
                .antMatchers("/").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .cors().and().
                csrf().disable();

        }


    }
