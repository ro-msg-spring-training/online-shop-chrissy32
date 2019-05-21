package ro.msg.learning.shop.integrationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrdersService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class OrderCreationIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private OrdersService ordersService;

    @Before
    public void initSuccessfulOrderCreationIntegrationTest() throws Exception {
        String uri = "/tests/populate";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

    @Test
    public void successCreatingOrder() {
        List<ProductQuantityDTO> products = new ArrayList<>();
        products.add(new ProductQuantityDTO(1, 2));
        products.add(new ProductQuantityDTO(2, 1));
        products.add(new ProductQuantityDTO(4, 5));

        Address address = new Address("USA", "Jonesboro", "Arkansas", "623 Arlington Avenue");
        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now().withNano(0), address, products);
        Order order = ordersService.createOrder(orderDTO);

        assert (order != null);
    }

    @Test(expected = RuntimeException.class)
    public void failedOrderCreation() {
        List<ProductQuantityDTO> products = new ArrayList<>();
        products.add(new ProductQuantityDTO(1, Integer.MAX_VALUE));

        Address address = new Address("USA", "Jamaica", "New York", "2434534 Pride Avenue");
        OrderDTO orderDTO = new OrderDTO(LocalDateTime.now().withNano(0), address, products);
        ordersService.createOrder(orderDTO);
    }

    @After
    public void clear() throws Exception {
        String uri = "/tests/clear";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

}
