package com.icommerce.service;

import com.icommerce.converter.ProductConverter;
import com.icommerce.dto.ProductDTO;
import com.icommerce.entity.ProductEntity;
import com.icommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            ProductEntity savedProductEntity = productRepository.findById(productDTO.getId()).get();
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
            productRepository.deleteById(item);
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
        ProductEntity productEntity = productRepository.findById(id).get();
        return productConverter.toDTO(productEntity);
    }


}
