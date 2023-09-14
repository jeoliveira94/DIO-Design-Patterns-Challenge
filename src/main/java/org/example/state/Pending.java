package org.example.state;

import org.example.domain.Order;
import org.example.domain.OrderStatus;

public class Pending implements OrderState{
    private final Order order;

    public Pending(Order order) {
        this.order = order;
    }

    @Override
    public void successInPaying() {
        var newStatus = OrderStatus.PAID;
        this.order.performStateTransition(newStatus);
    }

    @Override
    public void dispatchOrder() throws Exception {
        throw new Exception("State transition not allowed : CurrentState=" + order.getCurrentState());
    }

    @Override
    public void cancelOrder() {
        var newStatus = OrderStatus.CANCELLED;
        this.order.performStateTransition(newStatus);
    }
}
