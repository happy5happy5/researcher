package com.oneday.researcher.controller;

import com.oneday.researcher.entity.ApplicationUser;
import com.oneday.researcher.model.ApiResponse;
import com.oneday.researcher.model.LoginResponseDTO;
import com.oneday.researcher.model.RegistrationDTO;
import com.oneday.researcher.service.AuthenticationService;
import com.oneday.researcher.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {


    private final AuthenticationService authenticationService;
    private final TokenService tokenService;


    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetails userDetails , Model model) {
        log.info("[GET] /auth/login");
        if (userDetails != null) {
            userDetails.getAuthorities().forEach(System.out::println);
        }
//        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
// 현재 토큰 정보 가져오기
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            loginResponseDTO.setToken(token);
//        }
        return "pages/auth/login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("registrationDTO") @Valid RegistrationDTO body, BindingResult bindingResult,
                        Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        log.info("[POST] /auth/login");
        // 사용자 로그인 시도
        LoginResponseDTO loginResponseDTO = authenticationService.login(body.getUsername(), body.getPassword());
//        model.addAttribute("responseDTO", dto);

        if (loginResponseDTO != null && !loginResponseDTO.getToken().isEmpty()) {
// 로그인 성공시 유저정보와 토큰을 클라이언트에게 전달
            redirectAttributes.addFlashAttribute("loginResponseDTO", loginResponseDTO);
            return "redirect:/";
        } else {
            // 로그인 실패 시 에러 메시지를 모델에 추가
            model.addAttribute("loginError", "로그인에 실패했습니다.");
            return "pages/auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("[GET] /auth/logout");
        return "redirect:/auth/login";
    }

    @PostMapping("/validate")
    @ResponseBody
//    해더에서 토큰 받아서 유효한지 확인
    public ApiResponse<String> validate(@RequestHeader("Authorization") String bearerToken) {
        log.info("[POST] /auth/validate");
        tokenService.validateToken(bearerToken);
        if(tokenService.validateToken(bearerToken)) {
            return ApiResponse.success("유효한 토큰 입니다.", bearerToken);
        } else {
            return ApiResponse.error(401, "유효하지 않은 토큰입니다.");
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        log.info("[GET] /auth/register");
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "pages/auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid RegistrationDTO registrationDTO, BindingResult bindingResult, Model model, Errors errors) {
        log.info("[POST] /auth/register");
        if (bindingResult.hasErrors()) {
            log.info("error");
            return "pages/auth/register";
        }
        ApplicationUser user = authenticationService.registerUser(registrationDTO);
        if (user == null) {
            log.info("error");
            return "pages/auth/register";
        }
//        model.addAttribute("user", user);
//        이걸로 이름 칸에 이름 들어 가도록
        return "pages/auth/login";
    }
}
