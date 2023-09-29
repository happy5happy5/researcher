package com.oneday.researcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private final Logger logger = Logger.getLogger(AdminController.class.getName());

    @GetMapping("/test")
    public String admin() {
        logger.info("[GET] /admin");
        return "admin";
    }

}
