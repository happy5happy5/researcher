package com.oneday.researcher.controller;

import com.oneday.researcher.entity.ApplicationUser;
import com.oneday.researcher.model.LoginResponseDTO;
import com.oneday.researcher.model.RegistrationDTO;
import com.oneday.researcher.service.AuthenticationService;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
//@RestController
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
    public String loginPage(Model model) {
        logger.info("[GET] /auth/login");
//        model.addAttribute("login", new RegistrationDTO());
        return "login";
    }


//    @PostMapping("/login")
//    @ResponseBody
//    public LoginResponseDTO login(@RequestBody RegistrationDTO body) {
//        logger.info("[POST] /auth/login");
//        return authenticationService.login(body.getUsername(), body.getPassword());
//    }

    @PostMapping("/login")
    @ResponseBody
    public LoginResponseDTO login(@RequestBody @NotNull RegistrationDTO body) {
        logger.info("[POST] /auth/login");
        return authenticationService.login(body.getUsername(), body.getPassword());
    }

    @PostMapping("/register")
    @ResponseBody
    public ApplicationUser registerUser(@RequestBody @NotNull RegistrationDTO body) {
        logger.info("[POST] /auth/register");
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
}
