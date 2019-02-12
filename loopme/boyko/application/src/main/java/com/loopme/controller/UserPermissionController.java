package com.loopme.controller;

import com.loopme.model.UserPermission;
import com.loopme.service.IUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserPermissionController {

    @Autowired
    private IUserPermissionService userPermissionService;

    @RequestMapping("/getPublisherPermissions")
    @ResponseBody
    public List<UserPermission> getPublisherPermissions() {
        return userPermissionService.getPublisherPermissions();
    }

    @RequestMapping("/getAdminPermissions")
    @ResponseBody
    public List<UserPermission> getAdminPermissions() {
        return userPermissionService.getAdminPermissions();
    }

    @RequestMapping("/getOperatorPermissions")
    @ResponseBody
    public List<UserPermission> getOperatorPermissions() {
        return userPermissionService.getOperatorPermissions();
    }
}
