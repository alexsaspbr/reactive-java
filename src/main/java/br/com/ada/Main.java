package br.com.ada;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

/*        List<Integer> inteiros = List.of(1, 2, 3, 4, 5);

        //map - transformacao
        List<Integer> novosInteiros = inteiros.stream().map(numero -> numero * numero).collect(Collectors.toList());

        //filter - filtrar
        inteiros.stream().filter(numero -> numero % 2 == 0).collect(Collectors.toList());

        inteiros.stream().map(numero -> numero * numero)
                         .filter(numero -> numero % 2 == 0)
                         .collect(Collectors.toList());*/

        //exercicio 1

        /*Observable
                .range(1, 10000)
                .map(n -> n * n)
                .filter(n -> n % 2 == 0)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.printf("Numero %d", integer);
                    }
                });
*/
        //exercicio 2

        Observable<String> observable = Observable
                                            .just("ana", "alex", "arara", "thiane");
        /*observable
                .subscribe(Main::palindromo);*/
        
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
                .subscribe(System.out::print);
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