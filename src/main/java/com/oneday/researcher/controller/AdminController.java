package com.oneday.researcher.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;
@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {


    @GetMapping("/test")
    public String admin() {
        log.info("[GET] /admin");
        return "pages/admin/admin";
    }

//    admin page later

}
