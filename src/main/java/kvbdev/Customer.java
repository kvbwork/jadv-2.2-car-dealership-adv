package kvbdev;

public class Customer implements Runnable {
    protected final long DESIRE_TIMEOUT_MS = 500;

    private final String name;
    private final CarDealership dealership;

    public Customer(String name, CarDealership dealership) {
        this.name = name;
        this.dealership = dealership;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(name + " зашел в автосалон");
                Car car = dealership.get();
                System.out.println(name + " уехал на новеньком авто " + car);
                Thread.sleep(DESIRE_TIMEOUT_MS);
            }
        } catch (InterruptedException ex) {
        }
        System.out.println("Покупатель " + name + " закончил покупки");
    }
}
