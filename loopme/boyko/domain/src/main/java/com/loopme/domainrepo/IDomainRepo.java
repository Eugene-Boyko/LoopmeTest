package com.loopme.domainrepo;

import com.loopme.model.IEntityBO;

import java.util.List;

/**
 * Interface that forces repositories to use business layer api
 *
 * @param <T>  - type of business entity
 * @param <BK> - business key type of entity
 */
public interface IDomainRepo<T extends IEntityBO, BK> {
    T getByBusinessKey(BK businessKey);

    void saveOrUpdate(T businessObject);

    void delete(BK businessKey);

    List<T> getAll();
}
