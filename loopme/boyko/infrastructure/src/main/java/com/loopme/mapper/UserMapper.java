package com.loopme.mapper;

import com.loopme.dbmodel.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends IMapper<User, com.loopme.model.User> {
}