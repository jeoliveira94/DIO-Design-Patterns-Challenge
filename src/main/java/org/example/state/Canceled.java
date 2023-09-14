package org.example.state;

import org.example.domain.Order;

public class Canceled implements OrderState{
    private final Order order;

    public Canceled(Order order) {
        this.order = order;
    }

    @Override
    public void successInPaying() throws Exception {
        throw new Exception("State transition not allowed : CurrentState=" + order.getCurrentState());
    }

    @Override
    public void dispatchOrder() throws Exception {
        throw new Exception("State transition not allowed : CurrentState=" + order.getCurrentState());
    }

    @Override
    public void cancelOrder() throws Exception {
        throw new Exception("State transition not allowed : CurrentState=" + order.getCurrentState());
    }
}
