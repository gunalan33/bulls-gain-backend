package com.bullsgain.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Double buyPrice;

    @Column(nullable = false)
    private Integer quantity;


    private Double currentPrice;

    private Double profitLoss;

    private Double targetPrice;

    private Double stopLoss;

    @Column(nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
