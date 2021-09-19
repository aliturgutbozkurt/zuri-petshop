package com.turkninja.petshop.configure;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinaryService(){
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name","dvmlm2tno",
                        "api_key", "831382449959666",
                        "api_secret","2msq0ERl8F79lSnMMoArcSyppzw"
                )
        );
    }
}