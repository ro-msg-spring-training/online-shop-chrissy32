package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.exceptions.NoLocationFoundException;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.ILocationStrategy;

import java.util.*;

@Service
@AllArgsConstructor
public class OrdersService {
    private IOrderRepository orderRepository;
    private ICustomerRepository customerRepository;
    private IStockRepository stockRepository;
    private ILocationStrategy strategy;
    private IAddressRepository addressRepository;
    private IOrderDetailRepository orderDetailRepository;

    private List<Stock> runStrategy(OrderDTO order) {
        List<Stock> stocks = strategy.findLocation(order.getProducts());

        if (stocks == null || stocks.isEmpty())
            throw new NoLocationFoundException();

        return stocks;
    }

    public Order createOrder(OrderDTO orderDTO) {
        Address orderDTOAddress = orderDTO.getDeliveryAddress();
        Address existingAddress = addressRepository.findIDByCountryAndCityAndCountyAndStreet(orderDTOAddress.getAddressCountry(),
                orderDTOAddress.getAddressCity(), orderDTOAddress.getAddressCounty(), orderDTOAddress.getAddressStreet());

        final Address address;
        if (existingAddress == null) {
            addressRepository.save(orderDTOAddress);
            address = orderDTOAddress;
        }
        else {
            address = existingAddress;
        }

        List<Stock> stocks = runStrategy(orderDTO);

        Customer customer = customerRepository.findById(1).get();
        long size = orderRepository.count();

        List<ProductQuantityDTO> products = orderDTO.getProducts();

        stocks.forEach(stock -> {
            Order order = new Order(stock.getLocation(), customer, orderDTO.getTimestamp(), address);
            orderRepository.save(order);

            for(ProductQuantityDTO orderProduct : products) {
                if (orderProduct.getProductID().equals(stock.getProduct().getID())) {
                    Integer newQuantity = stock.getQuantity() - orderProduct.getQuantity();
                    stock.setQuantity(newQuantity);
                    stockRepository.save(stock);

                    OrderDetail orderDetail = new OrderDetail(order, stock.getProduct(), orderProduct.getQuantity());
                    orderDetailRepository.save(orderDetail);
                    break;
                }
            }
        });

        if (size == orderRepository.count())
            throw new OrderNotCreatedException();

        return new Order();
    }
}