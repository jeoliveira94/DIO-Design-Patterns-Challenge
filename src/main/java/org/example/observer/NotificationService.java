package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Observer {
    @Override
    public void update(String message) {
        System.out.println(NotificationService.class + " " + message);
    }
}
