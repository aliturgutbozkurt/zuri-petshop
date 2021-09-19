package com.turkninja.petshop.product.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.turkninja.petshop.ProductRepository;
import com.turkninja.petshop.api.response.product.CreateProductImageResponse;
import com.turkninja.petshop.api.response.product.GetProductImageResponse;
import com.turkninja.petshop.entity.product.ProductEntity;
import com.turkninja.petshop.product.ProductImageService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author ali turgut bozkurt
 * Created at 7/12/2021
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final Cloudinary cloudinary;
    private final ProductRepository productRepository;

    public ProductImageServiceImpl(Cloudinary cloudinary, ProductRepository productRepository) {
        this.cloudinary = cloudinary;
        this.productRepository = productRepository;
    }

    @Override
    public GetProductImageResponse getCategoryById(Long id) {
        return null;
    }

    @Override
    public Page<GetProductImageResponse> list() {
        return null;
    }

    @Override
    public String create(MultipartFile file, Long productId) {

        ProductEntity productEntity = productRepository.findById(productId);
        String url = upload(file);
        productEntity.setProductToImage();
        productRepository.save(productEntity);
        return "image added" + url;

    }

    @Override
    public void delete(Long id) {

    }

    public String upload(MultipartFile multipartFile) {


        try {
            File file = convert(multipartFile);
            Map uploadResult = this.cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private File convert(MultipartFile multipartFile) throws IOException {


        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream stream = new FileOutputStream(file);
        byte[] bytes = multipartFile.getBytes();
        stream.write(bytes);
        stream.close();
        return file;
    }

}
