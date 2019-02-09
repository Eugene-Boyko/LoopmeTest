package com.loopme.domainrepo;

public interface IDomainRepo<T, BK> {
    T getByBusinessKey(T type, BK businessKey);
}
