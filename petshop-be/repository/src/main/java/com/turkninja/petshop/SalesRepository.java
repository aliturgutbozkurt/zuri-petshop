package com.turkninja.petshop;

import com.turkninja.petshop.entity.sales.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
}