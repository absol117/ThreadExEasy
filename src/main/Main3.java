package main;

import java.util.concurrent.atomic.AtomicInteger;

public class Main3 {
    public static void main(String[] args) {

        /*
        Contatore condiviso:
                Crea un thread che incrementi una variabile condivisa in modo sincronizzato.
                Fai in modo che il thread incrementi la variabile più volte e poi stampi il risultato.
         */


        AtomicInteger atomicInteger = new AtomicInteger(0);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(atomicInteger.incrementAndGet());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(atomicInteger);

    }
}
