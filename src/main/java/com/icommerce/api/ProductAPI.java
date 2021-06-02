package com.icommerce.api;

import com.icommerce.dto.ProductDTO;
import com.icommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ProductDTO createProduct(@RequestBody ProductDTO model) {
        return productService.save(model);
    }

    @PutMapping(value = "/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO model, @PathVariable("id") long id) {
        model.setId(id);
        return productService.save(model);
    }

    @DeleteMapping()
    public void deleteProduct(@RequestBody long[] ids) {
        productService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public ProductDTO getProductById(@PathVariable("id") long id) {
        return productService.findOneById(id);
    }

    @GetMapping("/all")
    //@PreAuthorize("hasRole('USER')")
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }
}