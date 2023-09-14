package org.example.strategy;

public class VolumeDiscount implements DiscountStrategy{
    private final int volume;

    public VolumeDiscount(int volume) {
        this.volume = volume;
    }

    @Override
    public double getDiscount() {
        return switch (this.volume / 10) {
            case 0 -> 0.0;
            case 1 -> 0.03;
            case 2 -> 0.05;
            case 3 -> 0.8;
            case 4 -> 0.12;
            default -> 0.15;
        };
    }
}
