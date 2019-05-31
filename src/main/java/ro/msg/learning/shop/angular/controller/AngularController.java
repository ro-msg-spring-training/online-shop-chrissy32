package ro.msg.learning.shop.angular.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.angular.dto.CategoryDTO;
import ro.msg.learning.shop.angular.dto.SupplierDTO;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AngularController {
    private final IProductCategoryRepository productCategoryRepository;
    private final ISupplierRepository supplierRepository;

    @GetMapping("/categories")
    public List<CategoryDTO> allCategories() {
        return productCategoryRepository.findNameAndDescriptions().stream()
                .map(object -> new CategoryDTO(Integer.parseInt(object[0].toString()), object[1].toString(), object[2].toString()))
                .collect(Collectors.toList());
    }

    @GetMapping("/suppliers")
    public List<SupplierDTO> allSuppliers() {
        return supplierRepository.findNames().stream()
                .map(object -> new SupplierDTO(Integer.parseInt(object[0].toString()), object[1].toString()))
                .collect(Collectors.toList());
    }
}
