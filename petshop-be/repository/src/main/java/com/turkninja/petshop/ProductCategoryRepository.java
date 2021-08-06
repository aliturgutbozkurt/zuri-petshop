package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.product.ProductCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends ExtendedQueryDslJpaRepository<ProductCategoryEntity, Long> {
    Optional<ProductCategoryEntity> findByIdAndActiveTrue(Long parentId);

    Page<ProductCategoryEntity> findAllByActiveTrue(PageRequest pageRequest);

    Page<ProductCategoryEntity> findByParentIdAndActiveTrue(Long parentId, Pageable pageable);

    @Query("SELECT c FROM ProductCategoryEntity c WHERE c.parent.id is null and c.active = true ")
    Page<ProductCategoryEntity> findByParentIdIsNullAndActiveTrue(Pageable pageable);

    List<ProductCategoryEntity> findByParentIdAndActiveTrueOrderByNameAsc(Long parentId);
}
