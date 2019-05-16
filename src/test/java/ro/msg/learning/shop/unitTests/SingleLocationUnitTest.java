package ro.msg.learning.shop.unitTests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SingleLocationUnitTest {
    private IProductRepository productRepository = Mockito.mock(IProductRepository.class);
    @Mock
    private IStockRepository stockRepository;
    @InjectMocks
    private SingleLocationStrategy strategy;

    @Before
    public void initSingleLocationUnitTest() {
        MockitoAnnotations.initMocks(this);

        ProductCategory productCategory = new ProductCategory("convenience", "frequent purchase, little effort, low customer involvement");
        productCategory.setID(1);

        Supplier supplier = new Supplier("MWV Switzerland Ltd");
        supplier.setID(1);

        Product product = new Product("furniture", "curvilinear - bold, beautiful curves", new BigDecimal(90.08), 50d, productCategory, supplier, "https://www.dictionary.com/e/wp-content/uploads/2017/08/curvilinear_2.jpg");
        product.setID(1);
        Product secondProduct = new Product("someProduct", "curvilinear - bold, beautiful curves", new BigDecimal(90.08), 50d, productCategory, supplier, "https://www.dictionary.com/e/wp-content/uploads/2017/08/curvilinear_2.jpg");
        secondProduct.setID(2);

        when(productRepository.findById(product.getID())).thenReturn(Optional.of(product));
        when(productRepository.findById(secondProduct.getID())).thenReturn(Optional.of(secondProduct));

        Address address = new Address("USA", "Jamaica", "New York", "322 Pride Avenue");
        address.setID(1);

        Location location = new Location("someLocation", address);
        location.setID(1);
        Location secondLocation = new Location("someOtherLocation", address);
        secondLocation.setID(2);

        Stock stock = new Stock(product, location, 1000);
        stock.setID(1);

        List<Stock> mockStock = new ArrayList<>();
        mockStock.add(stock);

        when(stockRepository.findStocksByProductAndQuantity(product, 100)).thenReturn(mockStock);
    }

    @Test
    public void successRunningStrategy() {
        List<ProductQuantityDTO> lst = new ArrayList<>();
        lst.add(new ProductQuantityDTO(1, 100));

        List<Stock> stocks = strategy.findLocation(lst);

        assert (!stocks.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void failRunningStrategy() {
        List<ProductQuantityDTO> lst = new ArrayList<>();
        lst.add(new ProductQuantityDTO(1, 100));
        lst.add(new ProductQuantityDTO(2, 100));

        strategy.findLocation(lst);
    }
}
