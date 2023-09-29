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
public class Rsi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rsi_seq;

    private Integer rs_seq;
    private String rsi_question;
    private Integer rsi_no;
    private String rsi_type; // 0: 객관식 1: OX 2: likert 3: 주관식 4: 별점 5: 다중선택
    private String rsi_type0_1;
    private String rsi_type0_2;
    private String rsi_type0_3;
    private String rsi_type0_4;
    private String rsi_type0_5;
    private String rsi_type0_etc;
    private String rsi_type1;
    private String rsi_type2;
    private String rsi_type3;
    private String rsi_type4;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "rs_seq", referencedColumnName = "rs_seq", insertable = false, updatable = false)
    private Rs rs;

    // 일대다 관계 설정
    @OneToMany(mappedBy = "rsi", cascade = CascadeType.ALL)
    private List<Rsa> rsaList;

}

