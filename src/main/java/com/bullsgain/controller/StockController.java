package com.bullsgain.controller;

import com.bullsgain.dto.StockRequest;
import com.bullsgain.model.Stock;
import com.bullsgain.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public ResponseEntity<Stock> addStock(@RequestBody StockRequest request,
                                          Principal principal) {
        return ResponseEntity.ok(stockService.addStock(request, principal.getName()));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Stock>> getMyStocks(Principal principal) {
        return ResponseEntity.ok(stockService.getMyStocks(principal.getName()));
    }

    @PutMapping("/update-price/{stockId}")
    public ResponseEntity<Stock> updatePrice(@PathVariable Long stockId,
                                             @RequestParam Double currentPrice) {
        return ResponseEntity.ok(stockService.updateCurrentPrice(stockId, currentPrice));
    }

    @PutMapping("/buy-more/{stockId}")
    public ResponseEntity<Stock> buyMore(@PathVariable Long stockId,
                                         @RequestParam Integer quantity,
                                         @RequestParam Double price) {
        return ResponseEntity.ok(stockService.buyMore(stockId, quantity, price));
    }

    @PutMapping("/sell/{stockId}")
    public ResponseEntity<String> sell(@PathVariable Long stockId,
                                       @RequestParam Integer quantity) {
        return ResponseEntity.ok(stockService.sellStock(stockId, quantity));
    }

    @DeleteMapping("/delete/{stockId}")
    public ResponseEntity<String> deleteStock(@PathVariable Long stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.ok("Stock deleted successfully!");
    }

}
