package com.turkninja.petshop.api.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.turkninja.petshop.entity.answer.AnswerEntity;
import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.question.QuestionEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.entity.user.UserRoleEntity;
import com.turkninja.petshop.enums.Gender;
import com.turkninja.petshop.value.Address;
import com.turkninja.petshop.value.FullName;
import com.turkninja.petshop.value.Image;
import com.turkninja.petshop.value.Phone;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Response object for {@link UserEntity}.
 *
 * @author mstar
 * @version 1.0
 * @since 1.0, 2021-09-25
 */
@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String country;
    private String district;
    private String externalDoorNo;
    private String internalDoorNo;
    private String street;
    private String town;
    private String gender;
    private String lastLoggedIn;
    private String createdAt;
    private String revisedAt;
    private Image image;

    @JsonProperty("previewImageUrl")
    public String getPreviewImageUrl() {
        return image != null ? image.getImageUrl() : null;
    }
}
