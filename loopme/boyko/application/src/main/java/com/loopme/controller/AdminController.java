package com.loopme.controller;

import com.loopme.model.User;
import com.loopme.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping("/admin")
    @ResponseBody
    public User getPublisher() {
        return adminService.createPublisher("nameqwe", "maisa;d");
    }

}
