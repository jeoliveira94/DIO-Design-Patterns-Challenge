package org.example.domain;

import org.example.observer.Observer;
import org.example.observer.Subject;
import org.example.state.*;
import org.example.strategy.DiscountStrategy;

import java.util.*;


public class Order implements Subject {
    private OrderStatus currentStatus;
    private OrderState currentState;
    private final Map<OrderStatus, OrderState> states = new HashMap<>();
    private final List<Observer> observers = new ArrayList<>();
    private String message;

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

        this.message = "Payment of Order number xyx was a success";
        this.notifyObservers();

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

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(observer -> observer.update(this.message));
    }
}

