package com.loopme.model;

import lombok.Data;

import java.util.List;

@Data
public class Application implements IEntityBO {
    private String name;
    private ApplicationType applicationType;
    private List<ContentType> contentTypes;
    private User user;

    @Override
    public String getBusinessKey() {
        return name;
    }
}
