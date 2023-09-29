package com.oneday.researcher.util;


import com.oneday.researcher.entity.Role;
import com.oneday.researcher.entity.ApplicationUser;
import com.oneday.researcher.model.RegistrationDTO;
import com.oneday.researcher.repository.RoleRepository;
import com.oneday.researcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.logging.Logger;

@Component
public class DataLoadUtility implements CommandLineRunner {

    private final Logger logger = Logger.getLogger(DataLoadUtility.class.getName());

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoadUtility(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args){
        if(roleRepository.findByAuthority("ADMIN").isPresent()){
            return;
        }
        logger.info("DummyMaker.run");
        Role adminRole = roleRepository.save(new Role("ADMIN"));
        Role userRole = roleRepository.save(new Role("USER"));
        Set<Role> roles = Set.of(adminRole);
        Set<Role> roles2 = Set.of(userRole);
        Set<Role> roles3 = Set.of(adminRole, userRole);

        registerUser( "zombil8731", "1234", "윤종복", "zombil8731@gmail.com", "01090281679", roles3);
        registerUser( "songsong", "1234", "박송이", "songp875@gmail.com", "01040026862", roles);
        registerUser( "yhs000727", "1234", "양희수", "yhs000727@naver.com", "01094719727", roles);
        registerUser( "parksk", "1234", "박상균", "parksk@metabuild.co.kr", "01032337280", roles);
        registerUser( "okdol", "1234", "조옥현", "okdol@metabuild.co.kr", "01027074294", roles);
        registerUser( "alzack", "1234", "임예준", "alzack@kibwa.org", "01099340633", roles2);
        registerUser( "admin", "1234", "관리자", "test@test.com", "01012345678", roles3);
    }
    private void registerUser(String username, String password, String name, String email, String phone, Set<Role> role) {
        RegistrationDTO dto = new RegistrationDTO();
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setName(name);
        dto.setEmail(email);
        dto.setPhone(phone);

        ApplicationUser user = new ApplicationUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(role);
        userRepository.save(user);
    }

//    private void insertDummy(){
//        insertUser("zombil8731", "1234", "윤종복", "zombil8731@gmail.com", "01090281679", 2);
//        insertUser("songsong", "1234", "박송이", "songp875@gmail.com", "01040026862", 1);
//        insertUser("yhs000727", "1234", "양희수", "yhs000727@naver.com", "01094719727", 1);
//        insertUser("parksk", "1234", "박상균", "parksk@metabuild.co.kr", "01032337280", 1);
//        insertUser("okdol", "1234", "조옥현", "okdol@metabuild.co.kr", "01027074294", 1);
//        insertUser("alzack", "1234", "임예준", "alzack@kibwa.org", "01099340633", 1);
//    }

}


