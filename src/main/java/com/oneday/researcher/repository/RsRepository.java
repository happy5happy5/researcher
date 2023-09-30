package com.oneday.researcher.repository;

import com.oneday.researcher.entity.Rs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsRepository extends JpaRepository<Rs, Integer> {

}
