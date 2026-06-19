package com.bullsgain.service;

import com.bullsgain.dto.StockRequest;
import com.bullsgain.model.Stock;
import com.bullsgain.model.User;
import com.bullsgain.repository.StockRepository;
import com.bullsgain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    public Stock addStock(StockRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));


        Stock stock = new Stock();
        stock.setStockName(request.getStockName());
        stock.setSymbol(request.getSymbol());
        stock.setBuyPrice(request.getBuyPrice());
        stock.setQuantity(request.getQuantity());
        stock.setCurrentPrice(request.getBuyPrice());
        stock.setProfitLoss(0.0);
        stock.setTargetPrice(request.getTargetPrice());
        stock.setStopLoss(request.getStopLoss());

        stock.setUser(user);

        return stockRepository.save(stock);
    }

    public List<Stock> getMyStocks(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return stockRepository.findByUserId(user.getId());
    }

    public Stock updateCurrentPrice(Long stockId, Double currentPrice) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found!"));

        stock.setCurrentPrice(currentPrice);
        double profitLoss = (currentPrice - stock.getBuyPrice()) * stock.getQuantity();
        stock.setProfitLoss(profitLoss);

        return stockRepository.save(stock);
    }

    public Stock buyMore(Long stockId, Integer additionalQty, Double price) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found!"));

        double existingValue = stock.getBuyPrice() * stock.getQuantity();
        double newValue = price * additionalQty;
        int newQuantity = stock.getQuantity() + additionalQty;
        double weightedAvgPrice = (existingValue + newValue) / newQuantity;

        stock.setQuantity(newQuantity);
        stock.setBuyPrice(weightedAvgPrice);

        double profitLoss = (stock.getCurrentPrice() - weightedAvgPrice) * newQuantity;
        stock.setProfitLoss(profitLoss);

        return stockRepository.save(stock);
    }

    public String sellStock(Long stockId, Integer sellQty) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found!"));

        if (sellQty > stock.getQuantity()) {
            throw new RuntimeException("Cannot sell more than you own!");
        }

        double realizedPL = (stock.getCurrentPrice() - stock.getBuyPrice()) * sellQty;
        int remainingQty = stock.getQuantity() - sellQty;

        if (remainingQty == 0) {
            stockRepository.deleteById(stockId);
        } else {
            stock.setQuantity(remainingQty);
            stock.setProfitLoss((stock.getCurrentPrice() - stock.getBuyPrice()) * remainingQty);
            stockRepository.save(stock);
        }

        return String.format("Sold %d shares. Realized P&L: ₹%.2f", sellQty, realizedPL);
    }

    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }

    }
