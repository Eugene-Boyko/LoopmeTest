package com.loopme.service;

import com.loopme.model.User;

public interface IAdminService {
    User createPublisher(String name, String email);

    User updatePublisher(User publisher);

    void deletePublisher(String businessKey);

    User createOperator(String name, String email);

    User updateOperator(User operator);

    void deleteOperator(String businessKey);
}
