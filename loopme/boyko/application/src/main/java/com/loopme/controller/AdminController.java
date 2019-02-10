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

    @RequestMapping("/admin/createPublisher")
    @ResponseBody
    public User createPublisher(String name, String email) {
        return adminService.createPublisher(name, email);
    }

    @RequestMapping("/admin/updatePublisher")
    @ResponseBody
    public User updatePublisher(User publisher) {
        return adminService.updatePublisher(publisher);
    }

    @RequestMapping("/admin/deletePublisher")
    @ResponseBody
    public void deletePublisher(String businessKey) {
        adminService.deletePublisher(businessKey);
    }

    @RequestMapping("/admin/createOperator")
    @ResponseBody
    public User createOperator(String name, String email) {
        return adminService.createOperator(name, email);
    }

    @RequestMapping("/admin/updateOperator")
    @ResponseBody
    public User updateOperator(User operator) {
        return adminService.updateOperator(operator);
    }

    @RequestMapping("/admin/deleteOperator")
    @ResponseBody
    public void deleteOperator(String businessKey) {
        adminService.deleteOperator(businessKey);
    }
}
