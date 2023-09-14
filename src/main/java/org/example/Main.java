package org.example;


import org.example.domain.Order;
import org.example.observer.DeliveryService;
import org.example.observer.NotificationService;
import org.example.strategy.DiscountStrategy;
import org.example.strategy.FirstTimeBuyingDiscount;
import org.example.strategy.VolumeDiscount;

public class Main {
    public static void main(String[] args) throws Exception {
        DiscountStrategy firstTimeBuying = new FirstTimeBuyingDiscount();
        DiscountStrategy volumeDiscount = new VolumeDiscount(30);

        Order order = new Order();

        DeliveryService deliveryService = new DeliveryService();
        order.registerObserver(deliveryService);

        NotificationService notificationService = new NotificationService();
        order.registerObserver(notificationService);

        order.pay(firstTimeBuying, volumeDiscount);
        order.dispatch();
    }
}