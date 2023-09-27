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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String userid;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String gender;

    // 다대일 관계 설정
    @ManyToOne
    @JoinColumn(name = "role_id") // 참조 키 컬럼 이름 지정
    private Roles roles;

}

