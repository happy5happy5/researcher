package com.oneday.researcher.controller;


import com.oneday.researcher.model.RegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;


@Controller
public class HomeController {

    private final Logger logger = Logger.getLogger(HomeController.class.getName());

    @GetMapping("/" )
    public String home() {
        logger.info("[GET] /home");
        return "home";
    }
}
