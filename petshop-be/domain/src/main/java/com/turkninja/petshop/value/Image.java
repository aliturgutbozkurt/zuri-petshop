package com.turkninja.petshop.value;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

@Data
@ToString
public class Image {

    @Column(name = "image_url")
    private String imageUrl;
}
