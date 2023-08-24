package br.com.ada.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private List<Subscriber> subscribers;

    public Publisher() {
        this.subscribers = new ArrayList<>();
    }

    public void subscribe(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void notifySubscribers(String message) {
        for (Subscriber subscriber : subscribers) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    subscriber.update(message);
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }

    }

}
