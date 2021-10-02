package com.turkninja.petshop.user;

import com.turkninja.petshop.api.request.user.UserImageInsertRequest;
import com.turkninja.petshop.api.request.user.UserSignupRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.user.UserImageInsertResponse;
import com.turkninja.petshop.api.response.user.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void signUp(UserSignupRequest request);
    PageResponse<UserResponse> list(int page, int size);
    UserResponse getOne(Long id);
    void delete(Long id);
    UserImageInsertResponse insertUserImage(UserImageInsertRequest request);
}
