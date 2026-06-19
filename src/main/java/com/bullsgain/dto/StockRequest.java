package com.bullsgain.dto;

import lombok.Data;

@Data
public class StockRequest {

    private String stockName;
    private String symbol;
    private Double buyPrice;
    private Integer quantity;
    private Double targetPrice;
    private Double stopLoss;
}
