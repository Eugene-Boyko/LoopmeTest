package com.loopme.controller;

import com.loopme.model.User;
import com.loopme.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping("/createPublisher")
    @ResponseBody
    public User createPublisher(@RequestParam String name, @RequestParam String email) {
        return adminService.createPublisher(name, email);
    }

    @PostMapping("/updatePublisher")
    @ResponseBody
    public User updatePublisher(@RequestBody User publisher) {
        return adminService.updatePublisher(publisher);
    }

    @DeleteMapping("/deletePublisher")
    public void deletePublisher(@RequestParam String businessKey) {
        adminService.deletePublisher(businessKey);
    }

    @RequestMapping("/createOperator")
    @ResponseBody
    public User createOperator(@RequestParam String name, @RequestParam String email) {
        return adminService.createOperator(name, email);
    }

    @PostMapping("/updateOperator")
    @ResponseBody
    public User updateOperator(@RequestBody User operator) {
        return adminService.updateOperator(operator);
    }

    @DeleteMapping("/deleteOperator")
    public void deleteOperator(@RequestParam String businessKey) {
        adminService.deleteOperator(businessKey);
    }

    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }
}
