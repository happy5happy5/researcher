package com.oneday.researcher.controller;


import com.oneday.researcher.model.RegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Slf4j
@Controller
public class HomeController {


    @GetMapping("/" )
    public String home() {
        log.info("[GET] /home");
        return "home";
    }

    @GetMapping("/test")
    public String test() {
        log.info("[GET] /test");
        return "pages/research/test";
    }
}
