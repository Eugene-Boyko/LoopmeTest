package com.loopme.dao.impl;

import com.loopme.model.User;

public class UserDaoImpl extends GenericDaoHibernateImpl<com.loopme.dbmodel.User, User, String, String> {
    public UserDaoImpl(Class<com.loopme.dbmodel.User> typeClass) {
        super(typeClass);
    }
}