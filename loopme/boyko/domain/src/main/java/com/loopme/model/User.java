package com.loopme.model;

import lombok.Data;

@Data
public class User implements IEntityBO {
    private String name;
    private String email;
    private UserRole role;

    @Override
    public String getBusinessKey() {
        return name;
    }
}
