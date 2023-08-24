package br.com.ada.observer;

public class ClientSubscriber implements Subscriber {

    private String name;

    public ClientSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        sleep();
        System.out.printf("\n\nOlá %s, \nPROMOÇÃO IMPERDIVEL!!! \n%s", this.name, message);
    }

    private static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
