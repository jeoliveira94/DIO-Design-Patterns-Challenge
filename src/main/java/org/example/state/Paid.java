package org.example.state;

import org.example.domain.Order;
import org.example.domain.OrderStatus;

public class Paid implements OrderState{
    private final Order order;

    public Paid(Order order) {
        this.order = order;
    }

    @Override
    public void successInPaying() throws Exception {
        throw new Exception("State transition not allowed : CurrentState=" + order.getCurrentState());
    }

    @Override
    public void dispatchOrder() {
        var newStatus = OrderStatus.SENT;
        this.order.performStateTransition(newStatus);
    }

    @Override
    public void cancelOrder() {
        var newStatus = OrderStatus.CANCELLED;
        this.order.performStateTransition(newStatus);
    }
}
