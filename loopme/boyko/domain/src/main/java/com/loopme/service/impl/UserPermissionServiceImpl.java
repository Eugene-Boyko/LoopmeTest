package com.loopme.service.impl;

import com.loopme.model.UserPermission;
import com.loopme.service.IUserPermissionService;

import java.util.Arrays;
import java.util.List;

public class UserPermissionServiceImpl implements IUserPermissionService {

    @Override
    public List<UserPermission> getPublisherPermissions() {
        return Arrays.asList(UserPermission.CREATE_APPLICATION, UserPermission.UPDATE_APPLICATION, UserPermission.DELETE_APPLICATION);
    }

    @Override
    public List<UserPermission> getAdminPermissions() {
        return Arrays.asList(
                UserPermission.CREATE_PUBLISHER, UserPermission.UPDATE_PUBLISHER, UserPermission.DELETE_PUBLISHER,
                UserPermission.CREATE_OPERATOR, UserPermission.UPDATE_OPERATOR, UserPermission.DELETE_OPERATOR);
    }

    @Override
    public List<UserPermission> getOperatorPermissions() {
        return Arrays.asList(
                UserPermission.CREATE_PUBLISHER, UserPermission.UPDATE_PUBLISHER, UserPermission.DELETE_PUBLISHER,
                UserPermission.CREATE_APPLICATION, UserPermission.UPDATE_APPLICATION, UserPermission.DELETE_APPLICATION);
    }
}
