package com.oneday.researcher.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/test")
    public String hello() {
        log.info("[GET] /user/test");
        return "pages/user/user";
    }


//    My page later

}
