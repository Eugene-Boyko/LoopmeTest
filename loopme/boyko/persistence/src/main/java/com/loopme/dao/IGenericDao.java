package com.loopme.dao;

import com.loopme.dbmodel.IDaoModel;
import com.loopme.domainrepo.IDomainRepo;

import java.io.Serializable;

//T - type, PK - primary key, BK - business key

/**
 * Interface that provides common base methods for manipulations with database
 * @param <T> - type of DAO object
 * @param <PK> - primary key or database id
 * @param <BK> - business key that unique per each business entities type
 */
public interface IGenericDao <T extends IDaoModel, PK extends Serializable, BK> extends IDomainRepo<T, BK> {
    void saveOrUpdate(T objectInstance);
    T getById(PK id);
    void delete(T persistentObject);
}