package com.oneday.researcher.repository;

import com.oneday.researcher.entity.Rsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsiRepository extends JpaRepository<Rsi, Integer> {
}
