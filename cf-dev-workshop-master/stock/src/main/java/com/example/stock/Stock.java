package com.example.stock;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    private String ticker;
    private Double price;

    public Stock() {
    }

    public Stock(String ticker, Double price) {
        this.ticker = ticker;
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                '}';
    }
}
