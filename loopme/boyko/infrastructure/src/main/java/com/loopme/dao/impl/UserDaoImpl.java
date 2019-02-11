package com.loopme.dao.impl;

import com.loopme.dbmodel.User;
import com.loopme.domainrepo.IUserDomainRepo;
import com.loopme.model.UserRole;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl extends GenericDaoHibernateImpl<User, com.loopme.model.User, Long, String> implements IUserDomainRepo {
    public UserDaoImpl() {
        super(com.loopme.dbmodel.User.class);
    }

    @Override
    public List<com.loopme.model.User> getUsersByRole(UserRole userRole) {
        Query<User> getUsersByUserRoleQuery = getSession().createNamedQuery(
                User.USERS_BY_USER_ROLE_QUERY, com.loopme.dbmodel.User.class);
        getUsersByUserRoleQuery.setParameter("role", com.loopme.dbmodel.UserRole.valueOf(userRole.name()));
        return mapper.toBOs(getUsersByUserRoleQuery.getResultList());
    }
}