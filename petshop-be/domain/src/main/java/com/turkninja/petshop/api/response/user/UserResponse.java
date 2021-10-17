package com.turkninja.petshop.api.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.turkninja.petshop.value.Image;
import lombok.Data;


@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String name;
    private String lastName;
    private String email;
    private String phone;
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
