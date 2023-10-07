package main;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

public class Main2 {
    public static void main(String[] args) {

        /*
        Somma in parallelo:
                Crea due thread che calcolino la somma di due array di numeri in parallelo.
                Successivamente, combina i risultati per ottenere la somma totale.
         */



        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {6, 7, 8, 9, 10};

        // Variabile condivisa per la somma totale
        AtomicInteger sommaTotale = new AtomicInteger(0);



        // Creazione dei thread per calcolare la somma di ciascun array
        Thread thread1 = new Thread(() -> {
            int sommaParziale1 = 0;
            for (int num : array1) {
                sommaParziale1 += num;
            }
            sommaTotale.addAndGet(sommaParziale1);
        });

        Thread thread2 = new Thread(() -> {
            int sommaParziale2 = 0;
            for (int num : array2) {
                sommaParziale2 += num;
            }
            sommaTotale.addAndGet(sommaParziale2);
        });

        // Avvio dei thread
        thread1.start();
        thread2.start();

        try {
            // Attendi che entrambi i thread abbiano completato l'esecuzione
            thread1.join();
            thread2.join();

            // Ottieni la somma totale dall'AtomicInteger
            int risultatoFinale = sommaTotale.get();
            System.out.println("Somma totale: " + risultatoFinale);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

