package com.turkninja.petshop.user;

import com.turkninja.petshop.api.request.user.UserSignupRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public void signUp(UserSignupRequest request);

}
