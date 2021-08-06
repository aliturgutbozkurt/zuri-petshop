package com.turkninja.petshop;

import com.turkninja.petshop.base.ExtendedQueryDslJpaRepository;
import com.turkninja.petshop.entity.product.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends ExtendedQueryDslJpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByIdAndActiveTrue(Long parentId);

    Page<CategoryEntity> findAllByActiveTrue(PageRequest pageRequest);

    List<CategoryEntity> findByParentIdAndActiveTrue(Long parentId);

    Page<CategoryEntity> findByParentIdAndActiveTrue(Long parentId, Pageable pageable);

    @Query("SELECT c FROM CategoryEntity c WHERE c.parent.id is null and c.active = true ")
    Page<CategoryEntity> findByParentIdIsNullAndActiveTrue(Pageable pageable);
}
