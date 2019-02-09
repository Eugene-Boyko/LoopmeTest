package com.loopme.service.impl;

import com.loopme.domainrepo.IUserDomainRepo;
import com.loopme.exception.IncompatibleUserRoleException;
import com.loopme.exception.UpdateNonExistingUserException;
import com.loopme.model.User;
import com.loopme.model.UserRole;
import com.loopme.service.IAdminService;

public class AdminServiceImpl implements IAdminService {

    private static final String INCOMPATIBLE_USER_ROLE_MESSAGE = "Update operation performed on the user with incorrect role. User role: %s, but have to be %s";
    private static final String UPDATE_NON_EXISTING_USER_MESSAGE = "Update operation performed on non-existing user. User with business key: %s does not exist";

    private IUserDomainRepo userDomainRepo;

    public AdminServiceImpl(IUserDomainRepo userDomainRepo) {
        this.userDomainRepo = userDomainRepo;
    }

    @Override
    public User createPublisher(String name, String email) {
        return createUser(name, email, UserRole.PUBLISHER);
    }

    @Override
    public User updatePublisher(User publisher) {
        return updateUser(publisher, UserRole.PUBLISHER);
    }

    @Override
    public void deletePublisher(String businessKey) {
        userDomainRepo.delete(businessKey);
    }

    @Override
    public User createOperator(String name, String email) {
        return createUser(name, email, UserRole.OPERATOR);
    }

    @Override
    public User updateOperator(User operator) {
        return updateUser(operator, UserRole.OPERATOR);
    }

    @Override
    public void deleteOperator(String businessKey) {
        userDomainRepo.delete(businessKey);
    }

    private User createUser(String name, String email, UserRole userRole) {
        User user = new User(name, email, userRole);
        userDomainRepo.saveOrUpdate(user);
        return user;
    }

    private User updateUser(User user, UserRole userRole) {
        validateUser(user, userRole);
        User existingUser = userDomainRepo.getByBusinessKey(user.getBusinessKey());
        if (existingUser == null) {
            throw new UpdateNonExistingUserException(String.format(UPDATE_NON_EXISTING_USER_MESSAGE, user.getBusinessKey()));
        }
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        userDomainRepo.saveOrUpdate(existingUser);
        return existingUser;
    }

    private void validateUser(User user, UserRole userRole) {
        if (!user.getRole().equals(userRole)) {
            throw new IncompatibleUserRoleException(String.format(INCOMPATIBLE_USER_ROLE_MESSAGE, user.getRole().name(), userRole.name()));
        }
    }
}
