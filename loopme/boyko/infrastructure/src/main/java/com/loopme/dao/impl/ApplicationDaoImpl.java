package com.loopme.dao.impl;

import com.loopme.dbmodel.Application;
import com.loopme.domainrepo.IApplicationDomainRepo;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDaoImpl extends GenericDaoHibernateImpl<Application, com.loopme.model.Application, String, String> implements IApplicationDomainRepo {
    public ApplicationDaoImpl() {
        super(Application.class);
    }
}