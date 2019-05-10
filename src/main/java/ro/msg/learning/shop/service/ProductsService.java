package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductAndCategoryDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IProductCategoryRepository productCategoryRepository;
    @Autowired
    private ISupplierRepository supplierRepository;

    public ProductAndCategoryDTO create(ProductAndCategoryDTO item) {
        ProductCategory category = productCategoryRepository.findById(item.getCategory()).get();
        Supplier supplier = supplierRepository.findById(item.getSupplier()).get();

        Product product = new Product(item.getName(), item.getDescription(), item.getPrice(),
                item.getWeight(), category, supplier, item.getImageURL());

        productRepository.save(product);
        item.setID(product.getID());

        return item;
    }

    public ProductAndCategoryDTO update(Integer id, ProductAndCategoryDTO newItem) {
        ProductCategory category = productCategoryRepository.findById(newItem.getCategory()).get();
        Supplier supplier = supplierRepository.findById(newItem.getSupplier()).get();

        productRepository.findById(id).ifPresent( product -> {
            product.setName(newItem.getName());
            product.setDescription(newItem.getDescription());
            product.setPrice(newItem.getPrice());
            product.setWeight(newItem.getWeight());
            product.setCategory(category);
            product.setSupplier(supplier);
            product.setImageURL(newItem.getImageURL());
            productRepository.save(product);
        });

        return newItem;
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public List<ProductAndCategoryDTO> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductAndCategoryDTO> dtos = new ArrayList<>();

        products.forEach(product -> {
            ProductCategory category = product.getCategory();
            ProductAndCategoryDTO dto = new ProductAndCategoryDTO(product.getName(), product.getDescription(), product.getPrice(),
                    product.getWeight(), product.getCategory().getID(), product.getSupplier().getID(), product.getImageURL(),
                    category.getName(), category.getDescription());
            dto.setID(product.getID());
            dtos.add(dto);
        });

        return dtos;
    }

    public ProductAndCategoryDTO getOne(Integer id) {
        Product product = productRepository.findById(id).get();
        ProductCategory category = product.getCategory();

        ProductAndCategoryDTO one = new ProductAndCategoryDTO(product.getName(), product.getDescription(), product.getPrice(),
                product.getWeight(), product.getCategory().getID(), product.getSupplier().getID(), product.getImageURL(),
                category.getName(), category.getDescription());
        one.setID(id);

        return one;
    }
}
