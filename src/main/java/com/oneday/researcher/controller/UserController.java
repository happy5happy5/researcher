package com.oneday.researcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = Logger.getLogger(UserController.class.getName());
    @RequestMapping("/test")
    public String hello() {
        logger.info("[GET] /user/test");
        return "user";
    }

}
