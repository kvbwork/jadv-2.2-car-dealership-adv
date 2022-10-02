package kvbdev;

public class CarMaker implements Runnable {
    protected final long BUILD_TIME_MS = 300;

    private final String name;
    private final CarDealership dealership;
    private final int productionLimit;

    public CarMaker(String name, int productionLimit, CarDealership dealership) {
        this.name = name;
        this.productionLimit = productionLimit;
        this.dealership = dealership;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < productionLimit; i++) {
                Thread.sleep(BUILD_TIME_MS);
                Car car = new Car(name + "-" + i);
                System.out.println("Производитель " + name + " выпустил 1 авто: " + car);
                dealership.accept(car);
            }
            System.out.println("Производитель " + name + " завершил производство");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
