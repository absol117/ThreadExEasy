package main;

public class Main3_V02 {
    public static void main(String[] args) {

        Counter counter = new Counter();

        CounterThread counterThread = new CounterThread(counter);

        counterThread.start();
        try {
            counterThread.getThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n" + "Il valore finale del contatore è: " + counter.getValue());


    }


    public static class Counter {

        private int value = 0;

        public synchronized void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }
    }

    public static class CounterThread implements Runnable {

        private Counter counter;
        private Thread thread;

        public CounterThread(Counter counter) {
            this.counter = counter;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                counter.increment();
                System.out.println("Valore corrente del contatore: " + counter.getValue());
            }
        }

        public void start(){
            thread.start();
        }

        public Thread getThread() {
            return thread;
        }
    }


}
