package com.icommerce.converter;

import com.icommerce.entity.ProductEntity;
import com.icommerce.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter  {
    private ModelMapper modelMapper = new ModelMapper();

    public ProductEntity toEntity(ProductDTO productDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        return productEntity;
    }

    public ProductDTO toDTO(ProductEntity productEntity) {
        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
        return productDTO;
    }

    public ProductEntity toEntity(ProductDTO productDTO, ProductEntity productEntity) {
        productEntity = modelMapper.map(productDTO, ProductEntity.class);
        return productEntity;
    }
}
