package com.loopme.dbmodel;

public interface IDaoModel {
    String BUSINESS_KEY_COLUMN = "name";

    Long getId();

    default String getBusinessKeyColumn() {
        return BUSINESS_KEY_COLUMN;
    }
}
