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
public class Rsr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsr_seq;

    private Long rs_seq;
    private Long rsi_no;
    private String rsi_type;
    private String rsr_total;
    private Long rsr_type0_1;
    private Long rsr_type0_2;
    private Long rsr_type0_3;
    private Long rsr_type0_4;
    private Long rsr_type0_5;
    private String rsr_type0_etc_yn;
    private Long rsr_type1_o;
    private Long rsr_type1_x;
    private Long rsr_type2;
    private String rsr_type3_yn;
    private Long rsr_type4;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "rs_seq", referencedColumnName = "rs_seq", insertable = false, updatable = false)
    private Rs rs;

}

