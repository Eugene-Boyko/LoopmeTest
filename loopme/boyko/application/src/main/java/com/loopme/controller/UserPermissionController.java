package com.loopme.controller;

import com.loopme.model.UserPermission;
import com.loopme.service.IUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class UserPermissionController {

    @Autowired
    private IUserPermissionService userPermissionService;

    @GetMapping("/getPublisherPermissions")
    @ResponseBody
    public List<UserPermission> getPublisherPermissions() {
        return userPermissionService.getPublisherPermissions();
    }

    @GetMapping("/getAdminPermissions")
    @ResponseBody
    public List<UserPermission> getAdminPermissions() {
        return userPermissionService.getAdminPermissions();
    }

    @GetMapping("/getOperatorPermissions")
    @ResponseBody
    public List<UserPermission> getOperatorPermissions() {
        return userPermissionService.getOperatorPermissions();
    }
}
