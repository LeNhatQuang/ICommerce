package com.icommerce.dto;

import lombok.Data;

@Data
public class ProductDTO extends AbstractDTO<ProductDTO> {
    private String name;
    private String description;
    private String category;
    private String image;
}
