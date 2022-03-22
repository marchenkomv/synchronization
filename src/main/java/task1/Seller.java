package task1;

public class Seller {
    private Dealership dealership;
    private final long BUY_TIMEOUT = 8000;
    private final int FIRST_CAR_IN_LIST = 0;

    public Seller(Dealership dealership) {
        this.dealership = dealership;
    }

    public synchronized void sellCar() {
        String buyer = Thread.currentThread().getName();
        try {
            System.out.println(buyer + " зашел в салон");
            if (dealership.getCars().size() == 0) {
                System.out.println("Машин нет");
            }
            if (dealership.getSoldCars() == dealership.MAX_SOLD_CARS) {
                System.out.println("Салон закрывается, приходите завтра");
                return;
            }
            if (dealership.getCars().size() == 0) {
                wait(BUY_TIMEOUT);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        if (dealership.getCars().size() > 0) {
            System.out.println(buyer + " уехал на новеньком авто");
            dealership.addSoldCar();
            dealership.getCars().remove(FIRST_CAR_IN_LIST);
        } else {
            System.out.println(buyer + " не дождался машины, ушел домой");
        }
    }

    public synchronized void acceptCar() {
        dealership.getCars().add(new Car());
        System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил новое авто");
        notify();
    }
}