package com.loopme.dao;

import com.loopme.dbmodel.User;

public class UserDao extends GenericDaoHibernateImpl<User, String, String> {

    public UserDao(Class<User> typeClass) {
        super(typeClass);
    }
}
