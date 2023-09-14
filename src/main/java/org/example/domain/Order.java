package org.example.domain;

import org.example.state.*;
import org.example.strategy.DiscountStrategy;

import java.util.*;


public class Order {
    private OrderStatus currentStatus;
    private OrderState currentState;
    private final Map<OrderStatus, OrderState> states = new HashMap<>();

    public Order() {
        states.put(OrderStatus.PAID, new Paid(this));
        states.put(OrderStatus.SENT, new Sent(this));
        states.put(OrderStatus.CANCELLED, new Canceled(this));

        this.currentState = new Pending(this);
    }

    public void pay(DiscountStrategy... discountStrategies) throws Exception {
        var totalPrice = this.calculateTotalPrice();
        var totalPriceWithDiscount = this.calculateTotalWithDiscount(totalPrice, discountStrategies);
        this.currentState.successInPaying();


        System.out.printf("Total Price: %s%n", totalPrice);
        System.out.printf("With discounts the final price is: %s%n", totalPriceWithDiscount);
    }

    public void cancel() throws Exception {
       this.currentState.cancelOrder();
    }

    public void dispatch() throws Exception {
        this.currentState.dispatchOrder();
    }

    public double calculateTotalPrice() {
        return 25.76;
    }

    public double calculateTotalWithDiscount(double totalPrice, DiscountStrategy... discountStrategies) {
        for (DiscountStrategy strategy : discountStrategies) {
            var discount = strategy.getDiscount();
            totalPrice -= totalPrice * discount;
        }
        return totalPrice;
    }

    public void performStateTransition(OrderStatus newStatus) {
        this.currentStatus = newStatus;
        this.currentState = this.states.get(newStatus);
    }

    public OrderStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(OrderStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(OrderState currentState) {
        this.currentState = currentState;
    }

}

