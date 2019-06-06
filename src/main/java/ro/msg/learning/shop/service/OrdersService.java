package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.exceptions.NoLocationFoundException;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.ILocationStrategy;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class OrdersService {
    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final IStockRepository stockRepository;
    private final ILocationStrategy strategy;
    private final IAddressRepository addressRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final MailService mailService;

    private List<StockDTO> runStrategy(OrderDTO order) {
        return strategy.findLocation(order);
    }

    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        Address orderDTOAddress = orderDTO.getDeliveryAddress();
        Address existingAddress = addressRepository.findByAddressCountryAndAddressCityAndAddressCountyAndAddressStreet(orderDTOAddress.getAddressCountry(),
                orderDTOAddress.getAddressCity(), orderDTOAddress.getAddressCounty(), orderDTOAddress.getAddressStreet());

        final Address address;
        if (existingAddress == null) {
            addressRepository.save(orderDTOAddress);
            address = orderDTOAddress;
        }
        else {
            address = existingAddress;
        }

        List<StockDTO> stocks = runStrategy(orderDTO);

        if (stocks == null || stocks.isEmpty()) {
            throw new NoLocationFoundException();
        }

        Customer customer = customerRepository.findById(1).get();
        long size = orderRepository.count();

        Order order = new Order(stocks.get(0).getLocation(), customer, orderDTO.getTimestamp(), address);
        orderRepository.save(order);

        List<ProductQuantityDTO> products = orderDTO.getProducts();

        stocks.forEach(stock -> {
            for(ProductQuantityDTO orderProduct : products) {
                if (orderProduct.getProductID().equals(stock.getProduct().getID())) {
                    Integer newQuantity = stock.getQuantity() - orderProduct.getQuantity();

                    Stock updatedStock = stockRepository.findByProductAndLocation(stock.getProduct(), stock.getLocation());
                    updatedStock.setQuantity(newQuantity);

                    OrderDetail orderDetail = new OrderDetail(order, stock.getProduct(), orderProduct.getQuantity());
                    orderDetailRepository.save(orderDetail);
                    break;
                }
            }
        });

        if (size == orderRepository.count()) {
            throw new OrderNotCreatedException();
        }

        mailService.sendSimpleMessage(customer.getEmailAddress(), "Online Shop Order Confirmation",
                "Your order has been received!");
        return order;
    }
}