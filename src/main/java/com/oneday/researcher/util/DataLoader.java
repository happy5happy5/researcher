package com.oneday.researcher.util;


import com.oneday.researcher.entity.Roles;
import com.oneday.researcher.entity.User;
import com.oneday.researcher.repository.RoleRepository;
import com.oneday.researcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DataLoader implements CommandLineRunner {

    private final Logger logger = Logger.getLogger(DataLoader.class.getName());

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args){
        logger.info("DummyMaker.run");
        if (userRepository.count() == 0) {
            logger.info("DummyMaker.run: userRepository.count() == 0");
            insertDummy();
        }else{
            logger.info("DummyMaker.run: userRepository.count() != 0");
        }

    }

    private void insertDummy(){
        insertRole();
        insertUser("zombil8731", "1111", "윤종복", "zombil8731@gmail.com", "01090281679", 2L);
        insertUser("songsong", "1234", "박송이", "songp875@gmail.com", "01040026862", 1L);
        insertUser("yhs000727", "1234", "양희수", "yhs000727@naver.com", "01094719727", 1L);
        insertUser("parksk", "1234", "박상균", "parksk@metabuild.co.kr", "01032337280", 1L);
        insertUser("okdol", "1234", "조옥현", "okdol@metabuild.co.kr", "01027074294", 1L);
        insertUser("alzack", "1234", "임예준", "alzack@kibwa.org", "01099340633", 1L);
    }

    private void insertRole() {
        if (roleRepository.count() == 0) {
            logger.info("DummyMaker.run: userRepository.count() == 0");
            Roles role = new Roles();
            Roles role2 = new Roles();
            role.setRole_name("USER");
            role2.setRole_name("ADMIN");
            roleRepository.save(role);
            roleRepository.save(role2);
        }else{
            logger.info("DummyMaker.run: userRepository.count() != 0");
        }
    }

    private void insertUser(String userid, String password, String name, String email, String phone, Long role_id) {
        logger.info("DummyMaker.run: insertUser");
        if(userRepository.findUserByName(name) != null){
            return;
        }
        if (roleRepository.findById(role_id).isPresent()) {
            User user = new User();
            user.setUserid(userid);
            user.setPassword(passwordEncoder.encode(password));
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setRoles(roleRepository.findById(role_id).get());
            userRepository.save(user);
        }
    }
}


