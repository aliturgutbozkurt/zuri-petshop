package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.user.UserAddressCreateRequest;
import com.turkninja.petshop.api.response.user.UserAddressCreateResponse;
import com.turkninja.petshop.api.response.user.UserAddressGetResponse;
import com.turkninja.petshop.api.response.user.UserAddressUpdateResponse;
import com.turkninja.petshop.entity.user.UserAddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserAddressMapper.class})
public interface UserAddressMapper {
    @Mapping(source = "userId", target = "user.id")
    UserAddressEntity createRequestToEntity(UserAddressCreateRequest request);

    UserAddressCreateResponse entityToCreateResponse(UserAddressEntity entity);

    UserAddressUpdateResponse entityToUpdateResponse(UserAddressEntity entity);

    UserAddressGetResponse entityToGetResponse(UserAddressEntity entity);
}