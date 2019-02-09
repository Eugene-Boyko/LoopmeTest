package com.loopme.mapper;

import com.loopme.dbmodel.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ApplicationMapper extends IMapper<Application, com.loopme.model.Application> {
}
