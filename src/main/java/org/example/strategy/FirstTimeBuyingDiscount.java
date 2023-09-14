package org.example.strategy;

public class FirstTimeBuyingDiscount implements DiscountStrategy {
    @Override
    public double getDiscount() {
        return 0.1;
    }
}
