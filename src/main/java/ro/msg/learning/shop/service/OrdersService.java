package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.OrderProduct;
import ro.msg.learning.shop.exceptions.NoLocationFoundException;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.ILocationStrategy;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Service
public class OrdersService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IStockRepository stockRepository;
    @Autowired
    private ILocationStrategy strategy;
    @Autowired
    private IAddressRepository addressRepository;
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    private List<OrderProduct> productsList(OrderDTO order) {
        List<Integer> integers = order.getProducts();
        List<OrderProduct> products = new ArrayList<>();

        for(int i = 0; i < integers.size() - 1; i = i + 2) {
            OrderProduct product = new OrderProduct(integers.get(i), integers.get(i+1));
            products.add(product);
        }

        return products;
    }

    private List<Stock> runStrategy(OrderDTO order) throws Exception {
        List<OrderProduct> products = productsList(order);
        List<Stock> stocks = strategy.findLocation(products);

        if (stocks == null || stocks.isEmpty())
            throw new NoLocationFoundException();

        return stocks;
    }

    public Order createOrder(Integer customerID, OrderDTO orderDTO) throws Exception {
        String[] addressPieces = orderDTO.getDeliveryAddress().split(", ");
        Address address = new Address(addressPieces[0], addressPieces[1], addressPieces[2], addressPieces[3]);

        TypedQuery<Integer> query = entityManager.createQuery("SELECT ID FROM Address WHERE addressCountry = :addressCountry " +
                "AND addressCity = :addressCity AND addressCounty = :addressCounty AND addressStreet = :addressStreet", Integer.class);
        Integer addressID;

        try {
            addressID = query.setParameter("addressCountry", addressPieces[0])
                    .setParameter("addressCity", addressPieces[1])
                    .setParameter("addressCounty", addressPieces[2])
                    .setParameter("addressStreet", addressPieces[3]).getSingleResult();
            address.setID(addressID);
        } catch (NoResultException e) {
            addressRepository.save(address);
            addressID = address.getID();
        }

        List<Stock> stocks = runStrategy(orderDTO);

        Customer customer = customerRepository.findById(customerID).get();
        long size = orderRepository.count();

        List<OrderProduct> products = productsList(orderDTO);

        stocks.forEach(stock -> {
            Order order = new Order(stock.getLocation(), customer, orderDTO.getTimestamp(), address);
            orderRepository.save(order);

            for(OrderProduct orderProduct : products) {
                if (orderProduct.getID().equals(stock.getProduct().getID())) {
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