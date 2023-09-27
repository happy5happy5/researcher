package com.oneday.researcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Logger logger = Logger.getLogger(SecurityConfig.class.getName());


//    @Bean
//    public UserDetailsService userDetailsService() {
//        logger.info("userDetailsService");
//        return userid -> {
//            logger.info("userid: " + userid);
//            return null;
//        };
//    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**");
    }
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        logger.info("defaultSecurityFilterChain");
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/signup", "/css/**", "/images/**", "/js/**").permitAll()
                                .anyRequest().authenticated()
                )
//                .formLogin(withDefaults());
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                )   .logout(logout ->
                logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
        );
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        logger.info("filterChain");
//        http.authorizeHttpRequests(
//                request -> request
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers(
//                                "/",
//                                "/home",
//                                "/login",
//                                "/signup",
//                                "/css/**",
//                                "/images/**",
//                                "/js/**"
//                        )
//                        .permitAll()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .usernameParameter("userid")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                );
//        return http.build();
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
