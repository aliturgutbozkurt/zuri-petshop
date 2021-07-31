package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.user.UserSignupRequest;
import com.turkninja.petshop.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userSignupRequestToUserEntity(UserSignupRequest userSignupRequest);

}
