package com.loopme.dao.impl;

import com.loopme.dbmodel.User;
import com.loopme.domainrepo.IUserDomainRepo;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends GenericDaoHibernateImpl<User, com.loopme.model.User, String, String> implements IUserDomainRepo {
    public UserDaoImpl() {
        super(com.loopme.dbmodel.User.class);
    }
}