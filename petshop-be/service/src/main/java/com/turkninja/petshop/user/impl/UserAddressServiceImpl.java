package com.turkninja.petshop.user.impl;

import com.turkninja.petshop.UserAddressRepository;
import com.turkninja.petshop.api.request.user.UserAddressCreateRequest;
import com.turkninja.petshop.api.request.user.UserAddressUpdateRequest;
import com.turkninja.petshop.api.response.user.UserAddressCreateResponse;
import com.turkninja.petshop.api.response.user.UserAddressGetResponse;
import com.turkninja.petshop.api.response.user.UserAddressUpdateResponse;
import com.turkninja.petshop.entity.user.UserAddressEntity;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.UserAddressMapper;
import com.turkninja.petshop.user.UserAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAddressServiceImpl implements UserAddressService {
    private final UserAddressRepository repository;
    private final UserAddressMapper mapper;

    @Override
    public UserAddressCreateResponse create(@Valid UserAddressCreateRequest request) {
        log.info("Create a new user address by request:{}", request);

        UserAddressEntity userAddressEntity = mapper.createRequestToEntity(request);

        repository.save(userAddressEntity);

        return mapper.entityToCreateResponse(userAddressEntity);
    }

    @Override
    public UserAddressUpdateResponse update(Long id, @Valid UserAddressUpdateRequest request) {
        log.info("Update the user address by id:{} and request:{}", id, request);

        UserAddressEntity entity = findById(id);
        entity.setName(request.getName());
        entity.setProvince(request.getProvince());
        entity.setDistrict(request.getDistrict());
        entity.setNeighborhood(request.getNeighborhood());
        entity.setStreet(request.getStreet());
        entity.setBuilding(request.getBuilding());
        entity.setFloor(request.getFloor());
        entity.setApartment(request.getApartment());
        entity.setExplanation(request.getExplanation());

        repository.save(entity);

        return mapper.entityToUpdateResponse(entity);
    }

    @Override
    public void delete(Long id) {
        log.info("Delete the user address by id:{}", id);

        UserAddressEntity entity = repository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("id", id)));
        entity.setActive(false);

        repository.save(entity);
    }

    @Override
    public UserAddressGetResponse getById(Long id) {
        log.info("Get the user address by id:{}", id);

        UserAddressEntity entity = repository.findByIdAndActiveTrue(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                        AppParameter.get("id", id)));

        return mapper.entityToGetResponse(entity);
    }

    @Override
    public List<UserAddressGetResponse> getByUserId(Long userId) {
        log.info("Get all user addresses by userId:{}", userId);

        List<UserAddressEntity> entities = repository.findAllByUserIdAndActiveTrue(userId);

        return entities.stream().map(mapper::entityToGetResponse).collect(Collectors.toList());
    }

    private UserAddressEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ApplicationException(AppMessage.RECORD_NOT_FOUND, AppParameter.get("id", id)));
    }
}
