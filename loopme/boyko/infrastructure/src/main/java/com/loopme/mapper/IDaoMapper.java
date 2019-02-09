package com.loopme.mapper;

import com.loopme.dbmodel.IDaoEntity;
import com.loopme.model.IEntityBO;

import java.util.List;

public interface IDaoMapper<TD extends IDaoEntity, TB extends IEntityBO> {
    TD toDao(TB businessObject);

    List<TD> toDaos(List<TB> businessObject);

    TB toBO(TD daoObject);

    List<TB> toBOs(List<TD> daoObject);
}
