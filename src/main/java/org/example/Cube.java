package org.example;

import java.math.BigDecimal;

public class Cube {
    private String currency;
    private BigDecimal rate;

    public Cube() {
    }

    public Cube(String currency, BigDecimal rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
