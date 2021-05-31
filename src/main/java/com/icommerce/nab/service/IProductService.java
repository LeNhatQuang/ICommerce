package com.icommerce.nab.service;


import com.icommerce.nab.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
    void delete(long[] ids);
    List<ProductDTO> findAll();
    ProductDTO findOneById(long id);
}
