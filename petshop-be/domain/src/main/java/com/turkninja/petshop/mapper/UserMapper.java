package com.turkninja.petshop.mapper;

import com.turkninja.petshop.api.request.user.UserSignupRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.user.UserResponse;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.value.FullName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = FullName.class)
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userSignupRequestToUserEntity(UserSignupRequest userSignupRequest);

    @Mapping(target = "fullName",
            expression = "java(entity.getFullName().getName()" +
                    " + \" \" + entity.getFullName().getLastName())")
    @Mapping(target = "name",
            expression = "java(entity.getFullName().getName())")
    @Mapping(target = "lastName",
            expression = "java(entity.getFullName().getLastName())")
    @Mapping(target = "phone",
            expression = "java(entity.getPhone().getMobilePhone())")
    @Mapping(target = "lastLoggedIn",
            dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "createdAt",
            dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "revisedAt",
            dateFormat = "dd-MM-yyyy HH:mm:ss")
    UserResponse entityToUserResponse(UserEntity entity);

    default PageResponse<UserResponse> pageEntitiesToPageResponse(Page<UserEntity> pageOfUserEntity) {
        PageResponse<UserResponse> response = new PageResponse<UserResponse>();
        List<UserEntity> content = pageOfUserEntity.getContent();
        response.setContent(content.stream().map(this::entityToUserResponse).collect(Collectors.toList()));
        response.setTotalPages(pageOfUserEntity.getTotalPages());
        response.setTotalElements(pageOfUserEntity.getTotalElements());
        return response;
    }
}
