package com.oneday.researcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rsr_sub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsr_sub_seq;

    private Long rs_seq;
    private Long rsi_seq;
    private String rsi_no;
    private String rsr_sub_etc;
    private String rsr_sub_type3;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "rs_seq", referencedColumnName = "rs_seq", insertable = false, updatable = false)
    private Rs rs;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "rsi_seq", referencedColumnName = "rsi_seq", insertable = false, updatable = false)
    private Rsi rsi;

}

