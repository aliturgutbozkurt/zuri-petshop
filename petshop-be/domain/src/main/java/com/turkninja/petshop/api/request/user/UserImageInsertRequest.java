package com.turkninja.petshop.api.request.user;

import lombok.Data;

import java.util.Objects;

/**
 * Request object for user image creation.
 *
 * @author mstar
 * @version 1.0
 * @since 1.0, 2021-09-24
 */
@Data
public class UserImageInsertRequest {
    private Long userId;
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImageInsertRequest that = (UserImageInsertRequest) o;
        return Objects.equals(userId, that.userId) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, imageUrl);
    }
}
