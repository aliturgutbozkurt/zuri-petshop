package com.turkninja.petshop.user.impl;

import com.turkninja.petshop.UserRepository;
import com.turkninja.petshop.UserRoleRepository;
import com.turkninja.petshop.api.request.user.UserSignupRequest;
import com.turkninja.petshop.api.request.user.UserUpdateRequest;
import com.turkninja.petshop.api.response.common.PageResponse;
import com.turkninja.petshop.api.response.user.UserResponse;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.entity.user.UserRoleEntity;
import com.turkninja.petshop.enums.Gender;
import com.turkninja.petshop.exception.AppMessage;
import com.turkninja.petshop.exception.AppParameter;
import com.turkninja.petshop.exception.ApplicationException;
import com.turkninja.petshop.mapper.UserMapper;
import com.turkninja.petshop.user.UserService;
import com.turkninja.petshop.value.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public void signUp(UserSignupRequest request) {
        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        UserEntity userEntity = userMapper.userSignupRequestToUserEntity(request);
        userEntity.setPassword(encodedPassword);
        setDefaultUserRole(userEntity);
        userRepository.save(userEntity);
    }

    /**
     * Finds the user with given id and returns a UserResponse object.
     *
     * @param id Id of the user.
     * @return UserResponse representation of the found user.
     * @throws ApplicationException Returns RECORD_NOT_FOUND exception.
     */
    @Override
    public UserResponse getOne(Long id) throws ApplicationException {
        Optional<UserEntity> optionalUserEntity = userRepository
                .findByIdAndActiveTrue(id);
        if (!optionalUserEntity.isPresent()) {
            throw new ApplicationException(AppMessage.RECORD_NOT_FOUND);
        }

        return userMapper.entityToUserResponse(optionalUserEntity.get());
    }

    /**
     * Returns a paged response of users.
     *
     * @param page Page number.
     * @param size Size of page.
     * @return PageResponse representation of UserResponse.
     * @throws ApplicationException Returns ApplicationException.
     */
    @Override
    public PageResponse<UserResponse> list(int page, int size)
            throws ApplicationException {
        Page<UserEntity> entities = userRepository
                .findAllByActiveTrue(PageRequest.of(page, size, Sort.Direction.DESC, "fullName"));
        return userMapper.pageEntitiesToPageResponse(entities);
    }

    /**
     * Deactivates a user.
     *
     * @param id Id of the user.
     * @throws ApplicationException Returns RECORD_NOT_FOUND exception.
     */
    @Override
    public void delete(Long id)
            throws ApplicationException {
        UserEntity entity = userRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                                AppParameter.get("userId", id)));
        entity.setActive(false);
        userRepository.save(entity);
    }

    /**
     * Updates User entity with provided field values.
     *
     * @param id User id.
     * @param request An object holding updatable fields and values.
     * @throws ApplicationException Return RECORD_NOT_FOUND exception.
     */
    @Override
    public UserResponse update(Long id, @Valid UserUpdateRequest request)
            throws ApplicationException {
        UserEntity entity = userRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ApplicationException(AppMessage.RECORD_NOT_FOUND,
                                AppParameter.get("userId", id)));

        String name = request.getName();
        String lastName = request.getLastName();
        String genderValue = request.getGender();
        String mobilePhone = request.getMobilePhone();

        if (name != null && !name.isEmpty()) {
            entity.getFullName().setName(name);
        }

        if (lastName != null && !lastName.isEmpty()) {
            entity.getFullName().setLastName(lastName);
        }

        if (genderValue != null && !genderValue.isEmpty()) {
            Gender gender = genderValue.toLowerCase(Locale.ROOT).equals("e")
                    ? Gender.MALE : Gender.FEMALE;
            entity.setGender(gender);
        }

        if (mobilePhone != null && !mobilePhone.isEmpty()) {
            Phone phone = new Phone(mobilePhone);
            entity.setPhone(phone);
        }

        userRepository.save(entity);
        return userMapper.entityToUserResponse(entity);
    }

    private void setDefaultUserRole(UserEntity userEntity) {
        Optional<UserRoleEntity> defaultUserRoleOptional = userRoleRepository.findById(1L);
        UserRoleEntity userRoleEntity = null;
        if (defaultUserRoleOptional.isPresent()) {
            userRoleEntity = defaultUserRoleOptional.get();
            userRoleEntity.getUsers().add(userEntity);
        }
        userEntity.setRole(userRoleEntity);
    }
}
