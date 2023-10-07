package main;

public class Main1 {
    public static void main(String[] args) {

        /*
            Creazione di Thread: Scrivi un programma che crei due thread separati
            e ognuno stampi una serie di numeri da 1 a 10.
         */


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 5; i++) {
                    System.out.println("Thread N1: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 5; i++) {
                    System.out.println("Thread N2: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Esecuzione terminata");


    }
}