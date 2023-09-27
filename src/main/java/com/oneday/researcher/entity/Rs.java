package com.oneday.researcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rs_seq;

    private Long uid;
    private String rs_title;
    private String rs_desc;
    private Long rs_cnt;
    private String rs_start_date;
    private String rs_end_date;
    private String use_yn;
    private Long hits;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private User user;

    // 일대다 관계 설정
    @OneToMany(mappedBy = "rs")
    private List<Rsi> rsiList;

}

