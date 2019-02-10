package com.loopme.dao.impl;

import com.loopme.domainrepo.IApplicationDomainRepo;
import com.loopme.model.Application;
import com.loopme.model.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ApplicationDaoImpl extends GenericDaoHibernateImpl<com.loopme.dbmodel.Application, Application, Long, String> implements IApplicationDomainRepo {

    public ApplicationDaoImpl() {
        super(com.loopme.dbmodel.Application.class);
    }

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public void saveOrUpdate(Application businessObject) {
        User user = businessObject.getUser();
        com.loopme.dbmodel.User userDaoEntity = null;
        if (user != null) {
            userDaoEntity = userDao.saveOrMerge(user);
        }
        com.loopme.dbmodel.Application applicationDaoEntity = super.saveOrMerge(businessObject);
        applicationDaoEntity.setUser(userDaoEntity);
    }

    @Override
    public List<Application> getApplicationsByUserName(String userName) {
        Query<com.loopme.dbmodel.Application> getApplicationByUserNameQuery = getSession().createNamedQuery(
                com.loopme.dbmodel.Application.APPLICATIONS_BY_USER_NAME_QUERY, com.loopme.dbmodel.Application.class);
        getApplicationByUserNameQuery.setParameter("name", userName);
        return mapper.toBOs(getApplicationByUserNameQuery.getResultList());
    }
}