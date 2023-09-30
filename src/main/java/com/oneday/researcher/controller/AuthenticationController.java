package com.oneday.researcher.controller;

import com.oneday.researcher.entity.ApplicationUser;
import com.oneday.researcher.model.LoginResponseDTO;
import com.oneday.researcher.model.RegistrationDTO;
import com.oneday.researcher.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.logging.Logger;

@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {


    private final Logger logger = Logger.getLogger(AuthenticationController.class.getName());
    private final AuthenticationService authenticationService;


    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        logger.info("[GET] /auth/login");
//        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "login";
    }


//    @PostMapping("/login")
//    @ResponseBody
//    public LoginResponseDTO login(@RequestBody @NotNull RegistrationDTO body , Model model, HttpServletRequest res) {
//        logger.info("[POST] /auth/login");
//        LoginResponseDTO dto = authenticationService.login(body.getUsername(), body.getPassword());
//        if (!Objects.equals(dto.getToken(), "")) {
//            model.addAttribute("loginResponseDTO", res);
//            return dto;
//        }
//
//        return null;
//    }

    @PostMapping("/login")
    public String login(@NotNull RegistrationDTO body , Model model, HttpSession session, HttpServletResponse res) {
        logger.info("[POST] /auth/login");
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

    @PostMapping("/register")
    @ResponseBody
    public ApplicationUser registerUser(@NotNull RegistrationDTO body) {
        logger.info("[POST] /auth/register");
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
}
