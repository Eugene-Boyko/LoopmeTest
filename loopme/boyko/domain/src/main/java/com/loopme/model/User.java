package com.loopme.model;

import lombok.Data;

@Data
public class User {
    private String name;
    private String email;
    private UserRole role;
}
