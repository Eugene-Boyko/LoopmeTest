package com.loopme.service.impl;

import com.loopme.domainrepo.IApplicationDomainRepo;
import com.loopme.exception.UpdateNonExistingApplicationException;
import com.loopme.model.Application;
import com.loopme.model.ApplicationType;
import com.loopme.model.ContentType;
import com.loopme.model.User;
import com.loopme.service.IPublisherService;

import java.util.List;

public class PublisherServiceImpl implements IPublisherService {

    private IApplicationDomainRepo applicationDomainRepo;

    public PublisherServiceImpl(IApplicationDomainRepo applicationDomainRepo) {
        this.applicationDomainRepo = applicationDomainRepo;
    }

    @Override
    public Application createApplication(String name, ApplicationType applicationType, List<ContentType> contentTypes, User user) {
        Application application = new Application(name, applicationType, contentTypes, user);
        applicationDomainRepo.saveOrUpdate(application);
        return application;
    }

    @Override
    public Application updateApplication(Application application) {
        Application existingApplication = applicationDomainRepo.getByBusinessKey(application.getBusinessKey());
        validateApplicationForNull(application, existingApplication);
        existingApplication.setName(application.getName());
        existingApplication.setUser(application.getUser());
        existingApplication.setApplicationType(application.getApplicationType());
        existingApplication.setContentTypes(application.getContentTypes());
        applicationDomainRepo.saveOrUpdate(existingApplication);
        return existingApplication;
    }

    @Override
    public void deleteApplication(String businessKey) {
        applicationDomainRepo.delete(businessKey);
    }

    private void validateApplicationForNull(Application application, Application existingApplication) {
        if (existingApplication == null) {
            throw new UpdateNonExistingApplicationException(application.getBusinessKey());
        }
    }
}
