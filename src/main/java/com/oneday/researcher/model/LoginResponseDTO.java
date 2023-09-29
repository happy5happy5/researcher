package com.oneday.researcher.model;

import com.oneday.researcher.entity.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private ApplicationUser applicationUser;
    private String jwt;
}