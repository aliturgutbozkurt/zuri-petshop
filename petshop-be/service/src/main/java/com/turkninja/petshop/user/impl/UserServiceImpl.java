package com.turkninja.petshop.user.impl;

import com.turkninja.petshop.UserRepository;
import com.turkninja.petshop.UserRoleRepository;
import com.turkninja.petshop.api.request.user.UserSignupRequest;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.entity.user.UserRoleEntity;
import com.turkninja.petshop.mapper.UserMapper;
import com.turkninja.petshop.user.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (!userEntity.isPresent()) throw new UsernameNotFoundException(email);
        UserEntity user = userEntity.get();
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public void signUp(UserSignupRequest request) {
        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        UserEntity userEntity = userMapper.userSignupRequestToUserEntity(request);
        userEntity.setPassword(encodedPassword);
        setDefaultUserRole(userEntity);
        userRepository.save(userEntity);
    }



    private void setDefaultUserRole(UserEntity userEntity){
        Optional<UserRoleEntity> defaultUserRoleOptional = userRoleRepository.findById(1L);
        UserRoleEntity userRoleEntity = null;
        if (defaultUserRoleOptional.isPresent()){
            userRoleEntity = defaultUserRoleOptional.get();
            userRoleEntity.getUsers().add(userEntity);
        }
        userEntity.setRole(userRoleEntity);
    }
}
