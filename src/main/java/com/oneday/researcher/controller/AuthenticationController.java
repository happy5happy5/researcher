package com.oneday.researcher.controller;

import com.oneday.researcher.entity.ApplicationUser;
import com.oneday.researcher.model.LoginResponseDTO;
import com.oneday.researcher.model.RegistrationDTO;
import com.oneday.researcher.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.logging.Logger;

@Slf4j
@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {


    private final AuthenticationService authenticationService;


    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        log.info("[GET] /auth/login");
        return "fragments/login";
    }


    @PostMapping("/login")
    public String login(@NotNull RegistrationDTO body , Model model, HttpSession session, HttpServletResponse res) {
        log.info("[POST] /auth/login");
        LoginResponseDTO dto = authenticationService.login(body.getUsername(), body.getPassword());
        String token = dto.getToken();
        if (!Objects.equals(dto.getToken(), "")) {
            session.setAttribute("token", token);
            model.addAttribute("token", token);
            res.setHeader("Authorization", "Bearer " + token);
            return "home";
        }

        return "redirect:/auth/login?error=1";
    }

    @GetMapping("/register")
    public String register(Model model) {
        log.info("[GET] /auth/register");
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "fragments/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid RegistrationDTO registrationDTO, BindingResult bindingResult, Model model, Errors errors) {
        log.info("[POST] /auth/register");
        if (bindingResult.hasErrors()) {
            log.info("error");
            return "fragments/register";
        }
        ApplicationUser user = authenticationService.registerUser(registrationDTO);
        if (user == null) {
            log.info("error");
            return "fragments/register";
        }
//        model.addAttribute("user", user);
//        이걸로 이름 칸에 이름 들어 가도록
        return "redirect:/auth/login";
    }
}
