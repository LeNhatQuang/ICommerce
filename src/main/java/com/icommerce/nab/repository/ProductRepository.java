package com.icommerce.nab.repository;

import com.icommerce.nab.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findOneByName(String name);
}
