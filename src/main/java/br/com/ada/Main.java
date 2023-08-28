package br.com.ada;

import br.com.ada.observer.ClientSubscriber;
import br.com.ada.observer.LogSubscriber;
import br.com.ada.observer.Publisher;
import br.com.ada.shop.Shop;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> messages = Arrays.asList("Lava louças 8 Serviços 80% de desconto");

        Publisher publisher = new Publisher();
        ClientSubscriber clientSubscriber = new ClientSubscriber("Alex Araujo");
        ClientSubscriber clientSubscriber2 = new ClientSubscriber("Arthur");
        ClientSubscriber clientSubscriber3 = new ClientSubscriber("Marcelo");
        ClientSubscriber clientSubscriber4 = new ClientSubscriber("Thiane");
        LogSubscriber logSubscriber = new LogSubscriber();
        publisher.subscribe(clientSubscriber);
        publisher.subscribe(clientSubscriber2);
        publisher.subscribe(clientSubscriber3);
        publisher.subscribe(clientSubscriber4);
        publisher.subscribe(logSubscriber);
        Shop shop = new Shop(publisher);
        shop.sendPromotionMessages(messages);

    }
}