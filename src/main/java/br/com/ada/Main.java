package br.com.ada;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        //withScanner();
        //withFlux();


        ConnectableFlux<Object> connectableFlux = ConnectableFlux.create(cf -> {
            while(true){
                cf.next(System.currentTimeMillis());
            }
        }).publish();

        connectableFlux.subscribe(s -> System.out.println("Sub1 " + s));
        connectableFlux.subscribe(s -> System.out.println("Sub2 " + s));
        //connectableFlux.connect();

        Flux<String> flux = Flux.just("Mensagem 1");
        flux.subscribe(s -> System.out.println("Sub1 " + s));
        flux = flux.just("Mensagem 2");
        flux.subscribe(s -> System.out.println("Sub1 " + s));
        flux.subscribe(s -> System.out.println("Sub2 " + s));

        double end = (System.currentTimeMillis() - start) / 1000.0;

        System.out.printf("\n\n Tempo médio %.2f \n", end);

    }

    public static void withFlux(){
        Flux<String> flux = Flux.using(() -> new FileReader("/home/alexaraujo/ada/1012/reactive-java/dados.txt"),
                reader -> Flux.fromStream(new BufferedReader(reader).lines()),
                reader -> {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        flux.log()
                .parallel(8)
                .runOn(Schedulers.parallel());
                //.sequential()
                //.subscribe(System.out::println);

    }

    public static void withScanner(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("/home/alexaraujo/ada/1012/reactive-java/dados.txt"));
            List<String> linhas = new ArrayList<>();

            while(scanner.hasNext()) {
                System.out.println(scanner.next());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}