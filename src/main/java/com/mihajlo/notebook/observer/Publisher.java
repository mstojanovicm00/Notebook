package com.mihajlo.notebook.observer;

import java.util.function.Predicate;

public interface Publisher {
    void addSubscriber(Subscriber sub);
    void notifySubscribers(Object notification, Predicate<Subscriber> subscriberPredicate);

    default void notifySubscribers(Object notification) {
        this.notifySubscribers(notification, subscriber -> true);
    }
}
