package com.loopme.dao;

import com.loopme.dbmodel.IDaoModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository
@Transactional
public abstract class GenericDaoHibernateImpl<T extends IDaoModel, PK extends Serializable, BK> implements IGenericDao<T, PK, BK> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> typeClass;

    public GenericDaoHibernateImpl(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    @Transactional
    public void saveOrUpdate(T objectInstance) {
        sessionFactory.getCurrentSession().saveOrUpdate(objectInstance);
    }

    @Override
    @Transactional
    public T getById(PK id) {
        return sessionFactory.getCurrentSession().get(typeClass, id);
    }

    @Override
    @Transactional
    public void delete(T persistentObject) {
        sessionFactory.getCurrentSession().delete(persistentObject);
    }

    @Override
    @Transactional
    public T getByBusinessKey(T type, BK businessKey) {
        return sessionFactory.getCurrentSession()
                .byNaturalId(typeClass)
                .using(type.getBusinessKeyColumn(), businessKey)
                .load();
    }
}
