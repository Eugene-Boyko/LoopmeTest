package com.loopme.domainrepo;

import com.loopme.model.Application;

import java.util.List;

/**
 * Represents an abstract application repository
 */
public interface IApplicationDomainRepo extends IDomainRepo<Application, String> {
    List<Application> getApplicationsByUserName(String userName);
}
