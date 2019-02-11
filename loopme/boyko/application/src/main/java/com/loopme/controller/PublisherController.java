package com.loopme.controller;

import com.loopme.model.Application;
import com.loopme.model.ApplicationType;
import com.loopme.model.ContentType;
import com.loopme.model.User;
import com.loopme.service.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PublisherController {

    @Autowired
    private IPublisherService publisherService;

    @RequestMapping("/publisher/createApplication")
    @ResponseBody
    public Application createApplication(String name, ApplicationType applicationType, List<ContentType> contentTypes, User user) {
        return publisherService.createApplication(name, applicationType, contentTypes, user);
    }

    @RequestMapping("/publisher/updateApplication")
    @ResponseBody
    public Application updateApplication(Application application) {
        return publisherService.updateApplication(application);
    }

    @RequestMapping("/publisher/deleteApplication")
    @ResponseBody
    public void deleteApplication(String businessKey) {
        publisherService.deleteApplication(businessKey);
    }

    @RequestMapping("/publisher/getApplicationsByPublisherName")
    @ResponseBody
    public List<Application> getApplicationsByPublisherName(String publisherName) {
        return publisherService.getApplicationsByPublisherName(publisherName);
    }
}
