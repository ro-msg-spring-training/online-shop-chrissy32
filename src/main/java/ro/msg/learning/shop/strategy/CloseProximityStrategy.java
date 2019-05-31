package ro.msg.learning.shop.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;

import java.math.BigDecimal;
import java.util.*;

@AllArgsConstructor
public class CloseProximityStrategy implements ILocationStrategy {
    private final ILocationRepository locationRepository;
    private final IStockRepository stockRepository;
    private final IProductRepository productRepository;
    private final String apiKey;

    @Override
    public List<StockDTO> findLocation(OrderDTO orderDTO) {
        Iterable<Location> locations = locationRepository.findAll();
        Map<Location, BigDecimal> computedDistance = new HashMap<>();

        String deliveryAddress = " \"" + orderDTO.getDeliveryAddress().getAddressCity() + ", " + orderDTO.getDeliveryAddress().getAddressCountry() + "\"";

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://www.mapquestapi.com/directions/v2/routematrix?key=" + apiKey;

        locations.forEach(location -> {
            String locationAddress = " \"" + location.getAddress().getAddressCity() + ", " + location.getAddress().getAddressCountry() + "\"";
            HttpEntity<String> request = new HttpEntity<>("{ locations: [" + deliveryAddress + ", " + locationAddress + "]}");
            JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
            BigDecimal distance = response.get("distance").get(1).decimalValue();
            computedDistance.put(location, distance);
        });

        List<Map.Entry<Location, BigDecimal>> list = new ArrayList<>(computedDistance.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<Location, BigDecimal> closeProximityLocations = new LinkedHashMap<>();
        for (Map.Entry<Location, BigDecimal> entry : list) {
            closeProximityLocations.put(entry.getKey(), entry.getValue());
        }

        List<StockDTO> closestStocks = new ArrayList<>();

        orderDTO.getProducts().forEach(product -> {
            for (Map.Entry<Location,BigDecimal> entry : closeProximityLocations.entrySet()) {
                boolean found = false;
                List<Stock> availableStocks = stockRepository.findByLocationAndProductAndQuantity(entry.getKey(),
                        productRepository.findById(product.getProductID()).get(), product.getQuantity());

                if (!availableStocks.isEmpty()) {
                    Stock stock = availableStocks.get(0);
                    closestStocks.add(new StockDTO(stock.getLocation(), stock.getProduct(), stock.getQuantity()));
                    found = true;
                }

                if (found) {
                    break;
                }
            }
        });

        return closestStocks;
    }
}
