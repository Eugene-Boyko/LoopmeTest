package com.loopme.model;

import lombok.Data;

import java.util.List;

@Data
public class Application {
    private String name;
    private ApplicationType applicationType;
    private List<ContentType> contentTypes;
    private User user;
}
