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
public class Rsa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsa_seq;

    private Long rs_seq;
    private Long rsi_seq;
    private Long uid;
    private Long rsi_no;
    private String rsi_type;
    private String rsa_type0;
    private String rsa_type0_etc;
    private String rsa_type1;
    private String rsa_type2;
    private String rsa_type3;
    private String rsa_type4;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "rs_seq", referencedColumnName = "rs_seq", insertable = false, updatable = false)
    private Rs rs;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "rsi_seq", referencedColumnName = "rsi_seq", insertable = false, updatable = false)
    private Rsi rsi;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private User user;

}
