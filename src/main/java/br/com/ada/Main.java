package br.com.ada;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        //Observable - [0...N]
        //Single     - [0...1]

        /*Single<String> olaMundo = Single.just("Ola mundo");
        olaMundo = olaMundo.map(s -> s.concat(", Alex Araujo"));
        olaMundo.subscribe(System.out::println);*/

   StringBuilder sb = new StringBuilder();

        Observable<String> lettles = Observable.from(new String[]{"O", "a"});
        //lettles = lettles.toBlocking();
        lettles
                .map(l -> l.toUpperCase())
                //.observeOn(Schedulers.newThread())
                .doOnCompleted(System.out::println)
                .subscribe(emailSubscribe());

        System.out.println(sb.toString());

        /*PublishSubject<String> publishSubject = PublishSubject.create();
        //publishSubject.subscribeOn(Schedulers.newThread());
        publishSubject.subscribe(emailSubscribe());
        publishSubject.subscribe(logSubscriber());
        //publishSubject.onNext("O");
        //publishSubject.onNext("l");
        //publishSubject.onNext("a");
        //publishSubject.onNext(null);
        publishSubject.onCompleted();*/

    }

    private static Observer<String> emailSubscribe() {

        StringBuilder sb = new StringBuilder();

        return new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.printf("\nEnviando e-mails para clientes %s \n", sb.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.printf("\nOcorreu um erro %s", throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println(Thread.currentThread().getName());
                if(!"l".equalsIgnoreCase(s)) {
                    System.out.println(s);
                    sb.append(s);
                } else {
                    throw new NullPointerException();
                }
            }
        };
    }

    private static Observer<String> logSubscriber() {
        return new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Enviando logs para Elasticsearch");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.printf("\nOcorreu um erro %s", throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                //System.out.println(Thread.currentThread().getName());
                if(Objects.nonNull(s)) {
                    System.out.println(s + " Second Observer\n");
                } else {
                    throw new NullPointerException();
                }
            }
        };
    }
}