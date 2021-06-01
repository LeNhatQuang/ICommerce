package com.icommerce.api;

import com.icommerce.dto.ProductDTO;
import com.icommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/product")
    public ProductDTO createProduct(@RequestBody ProductDTO model) {
        return productService.save(model);
    }

    @PutMapping(value = "/product/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return productService.save(model);
    }

    @DeleteMapping(value = "/product")
    public void deleteProduct(@RequestBody long[] ids) {
        productService.delete(ids);
    }

    @GetMapping(value = "/product/{id}")
    public ProductDTO getProductById(@PathVariable("id") long id) {
        return productService.findOneById(id);
    }

    @GetMapping(value = "/products")
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }
}