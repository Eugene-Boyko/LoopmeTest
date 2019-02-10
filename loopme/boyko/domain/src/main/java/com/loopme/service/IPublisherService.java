package com.loopme.service;

import com.loopme.model.Application;
import com.loopme.model.ApplicationType;
import com.loopme.model.ContentType;
import com.loopme.model.User;

import java.util.List;

public interface IPublisherService {
    Application createApplication(String name, ApplicationType applicationType, List<ContentType> contentTypes, User user);

    Application updateApplication(Application application);

    void deleteApplication(String businessKey);

    List<Application> getMyApplications(String publisherName);
}
