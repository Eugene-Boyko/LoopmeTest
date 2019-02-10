package com.loopme.service.impl;

import com.loopme.domainrepo.IApplicationDomainRepo;
import com.loopme.domainrepo.IUserDomainRepo;
import com.loopme.exception.IncompatibleUserRoleException;
import com.loopme.exception.UpdateNonExistingApplicationException;
import com.loopme.exception.UpdateNonExistingUserException;
import com.loopme.model.*;
import com.loopme.service.IOperatorService;

import java.util.List;

public class OperatorServiceImpl implements IOperatorService {

    private IUserDomainRepo userDomainRepo;

    private IApplicationDomainRepo applicationDomainRepo;

    public OperatorServiceImpl(IUserDomainRepo userDomainRepo, IApplicationDomainRepo applicationDomainRepo) {
        this.userDomainRepo = userDomainRepo;
        this.applicationDomainRepo = applicationDomainRepo;
    }

    @Override
    public User createPublisher(String name, String email) {
        User user = new User(name, email, UserRole.PUBLISHER);
        userDomainRepo.saveOrUpdate(user);
        return user;
    }

    @Override
    public User updatePublisher(User publisher) {
        validateUser(publisher);
        User existingUser = userDomainRepo.getByBusinessKey(publisher.getBusinessKey());
        validateUserForNull(publisher, existingUser);
        existingUser.setName(publisher.getName());
        existingUser.setEmail(publisher.getEmail());
        userDomainRepo.saveOrUpdate(existingUser);
        return existingUser;
    }

    @Override
    public void deletePublisher(String businessKey) {
        userDomainRepo.delete(businessKey);
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

    @Override
    public List<Application> getAllApplications() {
        return applicationDomainRepo.getAll();
    }

    private void validateUser(User user) {
        if (!user.getRole().equals(UserRole.PUBLISHER)) {
            throw new IncompatibleUserRoleException(user.getRole().name(), UserRole.PUBLISHER.name());
        }
    }

    private void validateUserForNull(User publisher, User existingUser) {
        if (existingUser == null) {
            throw new UpdateNonExistingUserException(publisher.getBusinessKey());
        }
    }

    private void validateApplicationForNull(Application application, Application existingApplication) {
        if (existingApplication == null) {
            throw new UpdateNonExistingApplicationException(application.getBusinessKey());
        }
    }
}
