package com.loopme.controller;

import com.loopme.model.Application;
import com.loopme.model.ApplicationType;
import com.loopme.model.ContentType;
import com.loopme.model.User;
import com.loopme.service.IOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OperatorController {

    @Autowired
    private IOperatorService operatorService;

    @RequestMapping("/operator/createPublisher")
    @ResponseBody
    public User createPublisher(String name, String email) {
        return operatorService.createPublisher(name, email);
    }

    @RequestMapping("/operator/updatePublisher")
    @ResponseBody
    public User updatePublisher(User publisher) {
        return operatorService.updatePublisher(publisher);
    }

    @RequestMapping("/operator/deletePublisher")
    @ResponseBody
    public void deletePublisher(String businessKey) {
        operatorService.deletePublisher(businessKey);
    }

    @RequestMapping("/operator/createApplication")
    @ResponseBody
    public Application createApplication(String name, ApplicationType applicationType, List<ContentType> contentTypes, User user) {
        return operatorService.createApplication(name, applicationType, contentTypes, user);
    }

    @RequestMapping("/operator/updateApplication")
    @ResponseBody
    public Application updateApplication(Application application) {
        return operatorService.updateApplication(application);
    }

    @RequestMapping("/operator/deleteApplication")
    @ResponseBody
    public void deleteApplication(String businessKey) {
        operatorService.deleteApplication(businessKey);
    }
}
