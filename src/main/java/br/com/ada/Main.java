package br.com.ada;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Main {
    public static void main(String[] args) {

        //Observable [0...N] -> Flux
        //Single     [0...1] -> Mono


        //exercicio 1

        /*Flux
                .range(1, 10000)
                .map(n -> n * n)
                .filter(n -> n % 2 == 0)
                .subscribe(System.out::println);*/

        //exercicio 2

       /* Flux<String> observable = Flux
                                            .just("ana", "alex", "arara", "thiane");
        observable
                .subscribe(Main::palindromo);
        
        observable
                .map(palavra -> {

                    StringBuilder palavraInvertida = new StringBuilder();

                    for (int i = palavra.length() -1; i >= 0 ; i--) {
                        palavraInvertida.append(palavra.charAt(i));
                    }

                    if (palavra.equalsIgnoreCase(palavraInvertida.toString())){
                        return String.format("%s é palindromo\n", palavra).toUpperCase();
                    } else {
                        return String.format("%s não é palindromo\n", palavra).toUpperCase();
                    }

                })
                .subscribe(System.out::print);*/


        Flux<Integer> flux = Flux
                .range(1, 100);
        /*
        flux.subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        flux.subscribe(next -> System.out.println(next), //OnNext
                       error -> System.err.println(error), //OnError
                       complete -> System.out.println(complete)); //OnCompleted


        Flux.generate(
                () -> 0, (state, sink) -> {
                    sink.next(state);
                    if (state == 100)
                        sink.complete();
                    return state + 1;
                })
                .subscribe(System.out::println);

        Mono<String> mono = Mono.just("Alex");
        mono
                .log()
                .map(String::toUpperCase)
                .subscribe(System.out::println);*/

        flux = flux
                //.log()
                .map(n -> {
                    System.out.println("Por 2");
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    return n * 2;
                })
                //.publishOn(Schedulers.newParallel("paralelo", 4))
                .map(n -> {
                    System.out.println("Por 3");
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    return n * 3;
                })
                //.publishOn(Schedulers.newParallel("paralelo", 4))
                .map(n -> {
                    System.out.println("Por 4");
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    return n * 4;
                })
                .subscribeOn(Schedulers.newParallel("paralelo", 4));

                flux.subscribe(s -> {
                    System.out.println("Dado: " + s);
                    System.out.println("Thread: " + Thread.currentThread().getName());
                });


    }

    public static void palindromo(String palavra){
        StringBuilder palavraInvertida = new StringBuilder();
        for(int i = palavra.length() - 1; i >= 0; i--) {
            palavraInvertida.append(palavra.charAt(i));
        }

        if (palavra.equalsIgnoreCase(palavraInvertida.toString())){
            System.out.printf("%s é palindromo\n", palavra);
        } else {
            System.out.printf("%s não é palindromo\n", palavra);
        }

    }
}