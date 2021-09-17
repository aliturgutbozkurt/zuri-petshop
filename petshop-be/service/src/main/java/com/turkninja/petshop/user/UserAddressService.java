package com.turkninja.petshop.user;

import com.turkninja.petshop.api.request.user.UserAddressCreateRequest;
import com.turkninja.petshop.api.request.user.UserAddressUpdateRequest;
import com.turkninja.petshop.api.response.user.UserAddressCreateResponse;
import com.turkninja.petshop.api.response.user.UserAddressGetResponse;
import com.turkninja.petshop.api.response.user.UserAddressUpdateResponse;

import java.util.List;

public interface UserAddressService {
    UserAddressCreateResponse create(UserAddressCreateRequest request);

    UserAddressUpdateResponse update(Long id, UserAddressUpdateRequest request);

    void delete(Long id);

    UserAddressGetResponse getById(Long id);

    List<UserAddressGetResponse> getByUserId(Long userId);
}
