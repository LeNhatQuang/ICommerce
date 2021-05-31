package com.icommerce.nab.service;

import com.icommerce.nab.converter.ProductConverter;
import com.icommerce.nab.dto.ProductDTO;
import com.icommerce.nab.entity.ProductEntity;
import com.icommerce.nab.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements  IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity;

        if(productDTO.getId() != null){
            ProductEntity savedProductEntity = productRepository.findOne(productDTO.getId());
            productEntity = productConverter.toEntity(productDTO, savedProductEntity);
        } else {
            productEntity = productConverter.toEntity(productDTO);
        }

        /*if (!CollectionUtils.isEmpty(productEntity.getOrders())) {
            ProductEntity finalProductEntity = productEntity;
            List<ProductEntity> productEntities = new ArrayList<>();
            productEntities.add(finalProductEntity);
            productEntity.getOrders().forEach((block) -> {
                block.setProducts(productEntities);
            });
        }*/

        productEntity = productRepository.save(productEntity);
        return productConverter.toDTO(productEntity);
    }

    @Override
    public void delete(long[] ids) {
        for(long item: ids) {
            productRepository.delete(item);
        }
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(entity -> productConverter.toDTO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findOneById(long id) {
        ProductEntity productEntity = productRepository.findOne(id);
        return productConverter.toDTO(productEntity);
    }


}
