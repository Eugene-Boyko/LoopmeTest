package com.loopme.service;

import com.loopme.model.UserPermission;

import java.util.List;

public interface IUserPermissionService {
    List<UserPermission> getPublisherPermissions();

    List<UserPermission> getAdminPermissions();

    List<UserPermission> getOperatorPermissions();
}
