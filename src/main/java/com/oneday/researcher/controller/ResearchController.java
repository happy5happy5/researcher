package com.oneday.researcher.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/research")
public class ResearchController {



    @GetMapping("/list")
    public String list() {
        log.info("[GET] /research/list");
        return "pages/research/listform";
    }
}
