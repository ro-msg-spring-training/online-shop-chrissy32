package ro.msg.learning.shop.converter;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.controller.ProductsController;
import ro.msg.learning.shop.dto.ProductAndCategoryDTO;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ProductResourceAssembler implements ResourceAssembler<ProductAndCategoryDTO, Resource<ProductAndCategoryDTO>> {

    @Override
    public Resource<ProductAndCategoryDTO> toResource(ProductAndCategoryDTO product) {
        return new Resource<>(product,
                linkTo(methodOn(ProductsController.class).one(product.getID())).withSelfRel(),
                linkTo(methodOn(ProductsController.class).all()).withRel("products"));
    }
}
