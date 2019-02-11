package com.loopme.service;

import com.loopme.model.Application;
import com.loopme.model.ApplicationType;
import com.loopme.model.ContentType;
import com.loopme.model.User;

import java.util.List;

public interface IOperatorService {
    User createPublisher(String name, String email);

    User updatePublisher(User publisher);

    void deletePublisher(String businessKey);

    Application createApplication(String name, ApplicationType applicationType, List<ContentType> contentTypes, User user);

    Application updateApplication(Application application);

    void deleteApplication(String businessKey);

    List<Application> getAllApplications();

    List<User> getAllPublishers();
}
