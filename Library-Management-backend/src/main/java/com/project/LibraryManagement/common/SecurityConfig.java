//package com.project.LibraryManagement.common;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder)
//                .withUser("admin")
//                .password(encoder.encode("your_secure_password"))
//                .roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .permitAll();
//    }
//}
