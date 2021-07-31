package com.turkninja.petshop.product;

import com.turkninja.petshop.api.response.product.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductImageService {

    public GetProductImageResponse getCategoryById(Long id);

    public Page<GetProductImageResponse> list();

    public CreateProductImageResponse create(MultipartFile file, Long productId);

    public void delete(Long id);
}
