package com.loopme.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Helloooooo!!!!!=)))";
    }

    @RequestMapping("/nya")
    @ResponseBody
    public String sayNya() {
        return "Nya!!!!!=)))";
    }
}
