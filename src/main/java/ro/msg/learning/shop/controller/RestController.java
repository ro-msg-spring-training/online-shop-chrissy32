package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductResourceAssembler;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {
    private final IProductRepository productRepository;
    private final ProductResourceAssembler assembler;
    private  final IProductCategoryRepository productCategoryRepository;
    private final ILocationRepository locationRepository;
    private final ISupplierRepository supplierRepository;

    @GetMapping("/products")
    public Resources<Resource<Product>> all() {
        /*List<Resource<Product>> products = productRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(products,
                linkTo(methodOn(RestController.class).all()).withSelfRel());*/
        /*List<Product> products = (List<Product>) productRepository.findAll();
        productRepository.findAll().forEach(p -> {
            System.out.println(p);
        });*/
        List<ProductCategory> productCategories = (List<ProductCategory>) productCategoryRepository.findAll();
       // List<Location> locations = locationRepository.findAll();
      //  List<Supplier> suppliers = supplierRepository.findAll();
        return null;
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product newProduct) throws URISyntaxException {
        Resource<Product> resource = assembler.toResource(productRepository.save(newProduct));

        return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/products/{id}")
    public Resource<Product> one(@PathVariable Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return assembler.toResource(product);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product newProduct, @PathVariable Integer id) throws URISyntaxException {
        Product updatedProduct = productRepository.findById(id)
                .map(product -> {
                    product.setCategory(newProduct.getCategory());
                    product.setDescription(newProduct.getDescription());
                    product.setImageURL(newProduct.getImageURL());
                    product.setName(newProduct.getName());
                    product.setOrderDetails(newProduct.getOrderDetails());
                    product.setPrice(newProduct.getPrice());
                    product.setSupplier(newProduct.getSupplier());
                    product.setStocks(newProduct.getStocks());
                    product.setWeight(newProduct.getWeight());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setID(id);
                    return productRepository.save(newProduct);
                });

        Resource<Product> resource = assembler.toResource(updatedProduct);

        return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
