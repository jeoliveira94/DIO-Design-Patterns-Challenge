package org.example.state;

public interface OrderState {
    void successInPaying() throws Exception;
    void dispatchOrder() throws Exception;
    void cancelOrder() throws Exception;
}

