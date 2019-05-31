package ro.msg.learning.shop.unitTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.MostAbundantLocationStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MostAbundantUnitTest {
    @Mock
    private IProductRepository productRepository;
    @Mock
    private IStockRepository stockRepository;
    @InjectMocks
    private MostAbundantLocationStrategy strategy;

    @Before
    public void initMostAbundantLocationUnitTest() {
        MockitoAnnotations.initMocks(this);
        ProductCategory productCategory = new ProductCategory("convenience", "frequent purchase, little effort, low customer involvement");
        productCategory.setID(1);

        Supplier supplier = new Supplier("MWV Switzerland Ltd");
        supplier.setID(1);

        Product product = new Product("furniture", "curvilinear - bold, beautiful curves", new BigDecimal(90.08), 50d, productCategory, supplier, "https://www.dictionary.com/e/wp-content/uploads/2017/08/curvilinear_2.jpg");
        product.setID(1);

        when(productRepository.findById(any(Integer.class))).thenReturn(Optional.of(product));

        Address address = new Address("USA", "Jamaica", "New York", "322 Pride Avenue");
        address.setID(1);

        Location location = new Location("someLocation", address);
        location.setID(1);

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

        Address address = new Address("country", "city", "county", "street");
        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now().withNano(0), address, lst);

        List<StockDTO> stocks = strategy.findLocation(orderDTO);

        Assert.assertNotEquals(stocks, new ArrayList<>());
    }

    @Test(expected = RuntimeException.class)
    public void failRunningStrategy() {
        List<ProductQuantityDTO> lst = new ArrayList<>();
        lst.add(new ProductQuantityDTO(1, 10000));

        Address address = new Address("country", "city", "county", "street");
        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now().withNano(0), address, lst);

        strategy.findLocation(orderDTO);
    }
}
