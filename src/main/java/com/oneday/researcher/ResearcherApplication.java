package com.oneday.researcher;

import com.oneday.researcher.entity.ApplicationUser;
import com.oneday.researcher.entity.Role;
import com.oneday.researcher.repository.RoleRepository;
import com.oneday.researcher.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class ResearcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResearcherApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
//            System.out.println("CommandLineRunner run");
//            Role adminRole = roleRepository.save(new Role("ADMIN"));
//            roleRepository.save(new Role("USER"));
//
//            Set<Role> roles = Set.of(adminRole);
//
////            ApplicationUser admin = new ApplicationUser("yoon", passwordEncoder.encode("1234"), roles);
////            userRepository.save(admin);
//        };
//    }
}
