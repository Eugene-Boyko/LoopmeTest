package com.loopme.mapper;

import com.loopme.dbmodel.IDaoEntity;
import com.loopme.model.IEntityBO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Represents common mappers behavior on infrastructure layer
 *
 * @param <TD> - type of DAO entity
 * @param <TB> - type of business entity
 */
public interface IMapper<TD extends IDaoEntity, TB extends IEntityBO> {

    @Mapping(target = "id", ignore = true)
    TD toDao(TB businessObject);

    @InheritInverseConfiguration
    TB toBO(TD daoObject);

    List<TD> toDaos(List<TB> businessObjects);

    List<TB> toBOs(List<TD> daoObjects);
}
