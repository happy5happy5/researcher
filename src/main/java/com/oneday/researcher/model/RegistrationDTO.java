package com.oneday.researcher.model;


import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.beans.Encoder;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDTO {
    @NotNull(message = "아이디는 필수 입력 값 입니다.")
    @NotEmpty(message = "아이디는 필수 입력 값 입니다.")
    @NotBlank(message = "아이디는 필수 입력 값 입니다.")
    private String username;
    @NotNull(message = "이메일은 필수 입력 값 입니다.")
    @NotEmpty(message = "이메일은 필수 입력 값 입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @NotNull(message = "비밀번호는 필수 입력 값 입니다.")
    @NotEmpty(message = "비밀번호는 필수 입력 값 입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).*$", message = "비밀번호는 숫자, 소문자, 대문자, 특수 문자를 포함해야 합니다.")
    private String password;
    @NotNull(message = "비밀번호 확인은 필수 입력 값 입니다.")
    @NotEmpty(message = "비밀번호 확인은 필수 입력 값 입니다.")
//    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).*$", message = "비밀번호는 숫자, 소문자, 대문자, 특수 문자를 포함해야 합니다.")
    private String passwordConfirm;
    @NotNull(message = "이름은 필수 입력 값 입니다.")
    @NotEmpty(message = "이름은 필수 입력 값 입니다.")
    @Pattern(regexp = "^[가-힣]{2,5}$", message = "이름은 한글로 2~5자 이어야 합니다.")
    private String name;
    @NotNull(message = "전화번호는 필수 입력 값 입니다.")
    @NotEmpty(message = "전화번호는 필수 입력 값 입니다.")
    private String phone;
    private String gender;

}
