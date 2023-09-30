package com.oneday.researcher.service;

import com.oneday.researcher.entity.Role;
import com.oneday.researcher.entity.ApplicationUser;
import com.oneday.researcher.model.LoginResponseDTO;
import com.oneday.researcher.model.RegistrationDTO;
import com.oneday.researcher.repository.RoleRepository;
import com.oneday.researcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO login(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenService.generateJWT(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
        }catch (AuthenticationException e){
            return new LoginResponseDTO(null,"");
        }
    }


    public ApplicationUser registerUser(RegistrationDTO body) {
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        ApplicationUser applicationUser = new ApplicationUser(body);
        applicationUser.setPassword(passwordEncoder.encode(body.getPassword()));
        applicationUser.setAuthorities(authorities);
        return userRepository.save(applicationUser);
    }

    public ApplicationUser registerUser(String username, String password) {
        return null;
    }
}
