package by.epam.learn.io.PropDemo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class CyclicBarrierExample {
    private static CyclicBarrier Line;
    private static final int Line_size = 4;

    // Полосы
    public static class LineBoat implements Runnable {
        @Override
        public void run() {
            try {
                // Задержка на взлете
                System.out.println(
                        "\n Полоса приняла самолет");
                Thread.sleep(50);
                System.out.println(
                        "Полоса освободилась\n");
            } catch (InterruptedException e) {
            }
        }
    }

    // Класс автомобиля
    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                try {
                    System.out.printf(
                            "Самолет %d начал выход на полосу \n",
                            carNumber);
                    // Вызов метода await при подходе к
                    // барьеру; поток блокируется в ожидании
                    // прихода остальных потоков
                    Line.await(2, TimeUnit.SECONDS);
                } catch (Exception e) {
                }
                System.out.printf(
                        "Самолет %d взлетел\n",
                        carNumber);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        Line = new CyclicBarrier(Line_size,
                new LineBoat());
        for (int i = 1; i < 11; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }
}
