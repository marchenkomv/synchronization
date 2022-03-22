package task1;

public class Main {
    private static final int BUYER_COUNT = 3;

    public static void main(String[] args) {
        Dealership dealership = new Dealership();
        for (int i = 1; i <= BUYER_COUNT; i++) {
            new Thread(null, dealership::sellCar, "Покупатель" + i).start();
        }
        new Thread(null, dealership::addCar, "Toyota").start();
    }
}