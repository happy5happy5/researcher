package com.oneday.researcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("UserDetailsServiceImpl.loadUserByUsername");
        // 사용자 정보를 하드 코딩하여 반환 (테스트용)
        if ("testuser".equals(username)) {
            return User.builder()
                    .username("testuser")
                    .password(passwordEncoder.encode("testuser"))
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    // ... 다른 메서드들과 의존성 주입 코드
}
