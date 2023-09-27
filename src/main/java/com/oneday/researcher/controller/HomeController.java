package com.oneday.researcher.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;


@RequestMapping("/")
@Controller
public class HomeController {

    private final Logger logger = Logger.getLogger(HomeController.class.getName());

    @GetMapping({"/", "home"})
    public String home() {
        logger.info("[GET] /home");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        logger.info("[GET] /login");
        return "login";
    }

}
