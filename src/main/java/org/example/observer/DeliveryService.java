package org.example.observer;

public class DeliveryService implements Observer{
    @Override
    public void update(String message) {
        System.out.println(DeliveryService.class + " " + message);
    }
}
