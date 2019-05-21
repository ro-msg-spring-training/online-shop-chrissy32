package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductResourceAssembler;
import ro.msg.learning.shop.dto.ProductAndCategoryDTO;
import ro.msg.learning.shop.service.ProductsService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class ProductsController {
    private ProductResourceAssembler assembler;
    private final ProductsService productsService;

    @GetMapping("/products")
    public Resources<Resource<ProductAndCategoryDTO>> all() {
        List<Resource<ProductAndCategoryDTO>> products = productsService.getAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(products,
                linkTo(methodOn(ProductsController.class).all()).withSelfRel());
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductAndCategoryDTO newProduct) throws URISyntaxException {
        Resource<ProductAndCategoryDTO> resource = assembler.toResource(productsService.create(newProduct));

        return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/products/{id}")
    public Resource<ProductAndCategoryDTO> one(@PathVariable Integer id) {
        ProductAndCategoryDTO product = productsService.getOne(id);

        return assembler.toResource(product);

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
