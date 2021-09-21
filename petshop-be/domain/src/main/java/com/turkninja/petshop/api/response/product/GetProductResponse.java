package com.turkninja.petshop.api.response.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import com.turkninja.petshop.entity.product.ProductImageEntity;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */

@Data
public class GetProductResponse {

    private Long id;
    private String name;
    private String createdBy;
    private String about;
    private double oldPrice;
    private double price;
    private Set<GetProductImageResponse> images = new HashSet<>();
    private GetSoleCategoryResponse category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime revisedAt;

    @JsonProperty("previewImageUrl")
    public String getPreviewImageUrl(){
        if(images.size()>0){
            while (images.iterator().hasNext()) {
                if(!StringUtils.isEmpty(images.iterator().next())) {
                    return images.iterator().next().getUrl();
                }
            }
        }
        return null;
    }
}
