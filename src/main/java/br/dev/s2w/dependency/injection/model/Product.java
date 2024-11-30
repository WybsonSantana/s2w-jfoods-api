package br.dev.s2w.dependency.injection.model;

import java.math.BigDecimal;

public class Product {

private String name;
private BigDecimal totalValue;

    public Product(String name, BigDecimal totalValue) {
        this.name = name;
        this.totalValue = totalValue;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }
}
