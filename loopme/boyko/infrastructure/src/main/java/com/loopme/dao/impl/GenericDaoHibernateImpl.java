package com.loopme.dao.impl;

import com.loopme.dao.IGenericDao;
import com.loopme.dbmodel.IDaoEntity;
import com.loopme.mapper.IMapper;
import com.loopme.model.IEntityBO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public abstract class GenericDaoHibernateImpl<TD extends IDaoEntity, TB extends IEntityBO, PK extends Serializable, BK> implements IGenericDao<TB, PK, BK> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IMapper<TD, TB> mapper;

    private Class<TD> typeClass;

    GenericDaoHibernateImpl(Class<TD> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    @Transactional
    public void saveOrUpdate(TB businessObject) {
        getSession().saveOrUpdate(mapper.toDao(businessObject));
    }

    @Override
    @Transactional
    public TB getById(PK id) {
        return mapper.toBO(getSession().get(typeClass, id));
    }

    @Override
    @Transactional
    public void delete(BK businessKey) {
        TD load = getSession().bySimpleNaturalId(typeClass).load(businessKey);
        getSession().delete(load);
    }

    @Override
    @Transactional
    public TB getByBusinessKey(BK businessKey) {
        return mapper.toBO(getSession().bySimpleNaturalId(typeClass).load(businessKey));
    }

    @Override
    @Transactional
    public List<TB> getAll() {
        Session currentSession = getSession();
        CriteriaQuery<TD> criteriaQuery = currentSession.getCriteriaBuilder().createQuery(typeClass);
        criteriaQuery.from(typeClass);
        return mapper.toBOs(currentSession.createQuery(criteriaQuery).getResultList());
    }

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
