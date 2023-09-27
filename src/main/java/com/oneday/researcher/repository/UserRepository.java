package com.oneday.researcher.repository;


import com.oneday.researcher.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserid(String userid);

    User findUserByName(String name);


}
