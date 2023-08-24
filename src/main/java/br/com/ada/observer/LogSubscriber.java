package br.com.ada.observer;

import java.time.LocalDateTime;

public class LogSubscriber implements Subscriber {

    @Override
    public void update(String message) {
        System.out.printf("\n\n%tT [INFO] %s", LocalDateTime.now(), message);
    }

}
