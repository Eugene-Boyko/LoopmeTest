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
public abstract class GenericDaoHibernateImpl<TD extends IDaoEntity, TB extends IEntityBO, PK extends Serializable, BK> implements IGenericDao<TD, TB, PK, BK> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IMapper<TD, TB> mapper;

    private Class<TD> typeClass;

    GenericDaoHibernateImpl(Class<TD> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public void saveOrUpdate(TB businessObject) {
        saveOrMerge(businessObject);
    }

    @Override
    public TD saveOrMerge(TB businessObject) {
        TD objectToSave = mapper.toDao(businessObject);
        TD existingObject = getSession().bySimpleNaturalId(typeClass).load(businessObject.getBusinessKey());
        if (existingObject != null) {
            objectToSave.setId(existingObject.getId());
            objectToSave = typeClass.cast(getSession().merge(objectToSave));
        } else {
            getSession().saveOrUpdate(objectToSave);
        }
        return objectToSave;
    }

    @Override
    public TB getById(PK id) {
        return mapper.toBO(getSession().get(typeClass, id));
    }

    @Override
    public void delete(BK businessKey) {
        TD toDelete = getSession().bySimpleNaturalId(typeClass).load(businessKey);
        getSession().delete(toDelete);
    }

    @Override
    public TB getByBusinessKey(BK businessKey) {
        return mapper.toBO(getSession().bySimpleNaturalId(typeClass).load(businessKey));
    }

    @Override
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
