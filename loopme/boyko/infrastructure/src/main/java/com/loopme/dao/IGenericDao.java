package com.loopme.dao;

import com.loopme.dbmodel.IDaoEntity;
import com.loopme.domainrepo.IDomainRepo;
import com.loopme.model.IEntityBO;

import java.io.Serializable;

/**
 * Interface that provides common methods for manipulations with database based on business layer needs
 *
 * @param <TD> - type of dao object
 * @param <TB> - type of business object
 * @param <PK> - type of primary key or database id
 * @param <BK> - type of business key
 */
public interface IGenericDao<TD extends IDaoEntity, TB extends IEntityBO, PK extends Serializable, BK> extends IDomainRepo<TB, BK> {
    TB getById(PK id);
    TD saveOrMerge(TB businessObject);
}