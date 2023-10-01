package com.oneday.researcher.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class HomeController {


    @GetMapping("/" )
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        log.info("[GET] /home");
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("authorities", userDetails.getAuthorities());
            model.addAttribute("accountNonExpired", userDetails.isAccountNonExpired());
        }
        return "home";
    }

    @GetMapping("/test")
    public String test() {
        log.info("[GET] /test");
        return "pages/research/test";
    }
}
