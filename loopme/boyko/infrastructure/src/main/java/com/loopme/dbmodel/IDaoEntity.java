package com.loopme.dbmodel;

import java.io.Serializable;

public interface IDaoEntity extends Serializable {
    Long getId();
    void setId(Long id);
}
