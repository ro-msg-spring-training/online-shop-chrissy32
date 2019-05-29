package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductResourceAssembler;
import ro.msg.learning.shop.dto.ProductAndCategoryDTO;
import ro.msg.learning.shop.service.ProductsService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class ProductsController {
    private ProductResourceAssembler assembler;
    private final ProductsService productsService;

    @GetMapping("/products")
    public List<ProductAndCategoryDTO> all() {
        return productsService.getAll();
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductAndCategoryDTO newProduct) throws URISyntaxException {
        Resource<ProductAndCategoryDTO> resource = assembler.toResource(productsService.create(newProduct));

        return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/products/{id}")
    public ProductAndCategoryDTO one(@PathVariable Integer id) {
        return productsService.getOne(id);
    }

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestBody ProductAndCategoryDTO newProduct) throws URISyntaxException {
        ProductAndCategoryDTO updatedProduct = productsService.update(newProduct.getID(), newProduct);

        Resource<ProductAndCategoryDTO> resource = assembler.toResource(updatedProduct);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productsService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
