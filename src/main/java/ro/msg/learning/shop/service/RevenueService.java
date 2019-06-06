package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.RevenueDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Revenue;
import ro.msg.learning.shop.repository.IOrderDetailRepository;
import ro.msg.learning.shop.repository.IOrderRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IRevenueRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RevenueService {
    private final IRevenueRepository revenueRepository;
    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IProductRepository productRepository;

    @Transactional
    @Scheduled(cron = "50 59 23 * * *")
    public void createSalesReport() throws IOException {
        List<RevenueDTO> revenues = calculateRevenues(LocalDate.now());

        File file = new File("salesReport.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        revenues.forEach(revenue -> {
            try {
                writer.write(revenue.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();
    }

    @Transactional
    public List<RevenueDTO> calculateRevenues(LocalDate day) {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDateTime todayMidnight = LocalDateTime.of(day, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        List<Order> orders = orderRepository.findByCreatedAt(todayMidnight, tomorrowMidnight);

        orders.forEach(order -> {
            Revenue existingRevenue = revenueRepository.findByLocationAndDate(order.getShippedFrom(), LocalDate.now());
            final Revenue revenue;

            if (existingRevenue != null) {
                revenue = existingRevenue;
            }
            else {
                revenue = new Revenue(order.getShippedFrom(), day, new BigDecimal(0));
            }

            List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
            orderDetails.forEach(orderDetail -> {
                Product product = productRepository.findById(orderDetail.getProduct().getID()).get();
                int quantity = orderDetail.getQuantity();
                while (quantity > 0) {
                    revenue.setSum(revenue.getSum().add(product.getPrice()));
                    quantity--;
                }
            });
            revenueRepository.save(revenue);
        });

        List<Revenue> revenues = revenueRepository.findByDate(day);
        return revenues.stream()
                .map(revenue -> new RevenueDTO(revenue.getLocation(), revenue.getDate(), revenue.getSum()))
                .collect(Collectors.toList());
    }
}
