package com.loopme.controller;

import com.loopme.model.Application;
import com.loopme.model.ApplicationType;
import com.loopme.model.ContentType;
import com.loopme.model.User;
import com.loopme.service.IOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private IOperatorService operatorService;

    @RequestMapping("/createPublisher")
    @ResponseBody
    public User createPublisher(@RequestParam String name, @RequestParam String email) {
        return operatorService.createPublisher(name, email);
    }

    @PostMapping("/updatePublisher")
    @ResponseBody
    public User updatePublisher(@RequestBody User publisher) {
        return operatorService.updatePublisher(publisher);
    }

    @DeleteMapping("/deletePublisher")
    public void deletePublisher(@RequestParam String businessKey) {
        operatorService.deletePublisher(businessKey);
    }

    @RequestMapping("/createApplication")
    @ResponseBody
    public Application createApplication(@RequestParam String name,
                                         @RequestParam ApplicationType applicationType,
                                         @RequestParam List<ContentType> contentTypes,
                                         @RequestParam User user) {
        return operatorService.createApplication(name, applicationType, contentTypes, user);
    }

    @PostMapping("/updateApplication")
    @ResponseBody
    public Application updateApplication(@RequestBody Application application) {
        return operatorService.updateApplication(application);
    }

    @DeleteMapping("/deleteApplication")
    public void deleteApplication(@RequestParam String businessKey) {
        operatorService.deleteApplication(businessKey);
    }

    @GetMapping("/getAllApplications")
    @ResponseBody
    public List<Application> getAllApplications() {
        return operatorService.getAllApplications();
    }

    @GetMapping("/getAllPublishers")
    @ResponseBody
    public List<User> getAllPublishers() {
        return operatorService.getAllPublishers();
    }
}
