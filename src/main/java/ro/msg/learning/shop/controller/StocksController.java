package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.converter.CSVConverter;

import ro.msg.learning.shop.service.StocksService;

import java.io.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class StocksController {
    private final StocksService stocksService;
    private CSVConverter csvConverter;

    @GetMapping("/stocks/{id}")
    public void exportStock(@PathVariable Integer id) throws IOException {
        List<Integer> stocks = stocksService.exportStock(id);
        OutputStream file = new FileOutputStream("output.csv");
        csvConverter.toCsv(Integer.class, stocks, file);
    }
}
