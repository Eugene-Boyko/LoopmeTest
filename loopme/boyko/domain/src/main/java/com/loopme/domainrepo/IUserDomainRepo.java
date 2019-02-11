package com.loopme.domainrepo;

import com.loopme.model.User;
import com.loopme.model.UserRole;

import java.util.List;

/**
 * Represents an abstract user repository
 */
public interface IUserDomainRepo extends IDomainRepo<User, String> {
    List<User> getUsersByRole(UserRole userRole);
}
