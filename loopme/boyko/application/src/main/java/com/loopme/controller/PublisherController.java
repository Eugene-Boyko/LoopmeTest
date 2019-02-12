package com.loopme.controller;

import com.loopme.model.Application;
import com.loopme.model.ApplicationType;
import com.loopme.model.ContentType;
import com.loopme.model.User;
import com.loopme.service.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private IPublisherService publisherService;

    @GetMapping("/createApplication")
    @ResponseBody
    public Application createApplication(@RequestParam String name,
                                         @RequestParam ApplicationType applicationType,
                                         @RequestParam List<ContentType> contentTypes,
                                         @RequestParam User user) {
        return publisherService.createApplication(name, applicationType, contentTypes, user);
    }

    @PostMapping("/updateApplication")
    @ResponseBody
    public Application updateApplication(@RequestBody Application application) {
        return publisherService.updateApplication(application);
    }

    @DeleteMapping("/deleteApplication")
    public void deleteApplication(@RequestParam String businessKey) {
        publisherService.deleteApplication(businessKey);
    }

    @GetMapping("/getApplicationsByPublisherName")
    @ResponseBody
    public List<Application> getApplicationsByPublisherName(String publisherName) {
        return publisherService.getApplicationsByPublisherName(publisherName);
    }
}
