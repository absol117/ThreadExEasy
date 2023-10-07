package main;

import java.nio.Buffer;
import java.util.ArrayList;

public class Main4 {
    public static void main(String[] args) {

        /*
        Produttore-Consumatore:
                Implementa una soluzione per il problema del produttore-consumatore utilizzando i thread.
                Usa un buffer condiviso e due tipi di thread: produttori che inseriscono dati e consumatori che li leggono.
         */



        BufferMagazin bufferMagazin = new BufferMagazin();

        Prodduttore prodduttore = new Prodduttore(bufferMagazin);
        Cliente cliente = new Cliente(bufferMagazin);

        prodduttore.start();
        cliente.start();
        try {
            prodduttore.getThread().join();
            cliente.getThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Fine programma");

    }

    public static class Dato {
        private int id;
        private String content;

        public Dato(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public Dato() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Dato{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public static class BufferMagazin {
        private ArrayList<Dato> dati = new ArrayList<>();

        public synchronized void inserisci(Dato dato) {
            dati.add(dato);
            notify();
        }

        public synchronized Dato leggi() {
            while (dati.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return dati.remove(0);
        }
    }

    public static class Prodduttore implements Runnable{

        private Thread thread;
        private BufferMagazin magazin;
        private Dato dato;

        public Prodduttore(BufferMagazin magazin) {
            this.magazin = magazin;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                this.dato = new Dato();
                dato.setId(i);
                dato.setContent(String.valueOf('a' + i));
                magazin.inserisci(dato);
                System.out.println("INSERIMENTO DATO: ID " + dato.getId() + " Contenuto: " + dato.getContent());
            }
        }

        public void start() {
            thread.start();
        }

        public Thread getThread() {
            return thread;
        }

        public BufferMagazin getMagazin() {
            return magazin;
        }


    }

    public static class Cliente implements Runnable{


        private Thread thread;
        private BufferMagazin magazin;
        private Dato dato;


        public Cliente(BufferMagazin magazin) {
            this.magazin = magazin;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Dato letto = magazin.leggi();
                System.out.println("LETTURA DATO: ID " + letto.getId() + " Contenuto: " + letto.getContent());
            }
        }

        public void start() {
            thread.start();
        }

        public Thread getThread() {
            return thread;
        }

        public BufferMagazin getMagazin() {
            return magazin;
        }


    }




}
