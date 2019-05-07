package ro.msg.learning.shop.converter;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.controller.RestController;
import ro.msg.learning.shop.model.Product;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ProductResourceAssembler implements ResourceAssembler<Product, Resource<Product>> {

    @Override
    public Resource<Product> toResource(Product product) {
        return new Resource<>(product,
                linkTo(methodOn(RestController.class).one(product.getID())).withSelfRel(),
                linkTo(methodOn(RestController.class).all()).withRel("products"));
    }
}
