package com.oneday.researcher.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationDTO {
    private String username;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String gender;
}
