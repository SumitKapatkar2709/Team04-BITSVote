//package com.bits.bitsvote.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//
//    @Autowired
//    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .requestMatchers("/home").authenticated()
//                .anyRequest().permitAll()
//                .and()
//            .oauth2Login()
//                .successHandler(customAuthenticationSuccessHandler)
//                .and()
//            .exceptionHandling()
//                .accessDeniedPage("/access-denied"); // Configure access denied page
//    }
//}
//
