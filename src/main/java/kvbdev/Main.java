package kvbdev;

public class Main {

    public static void main(String[] args) throws Exception {

        final int PRODUCTION_LIMIT = 10;

        CarDealership dealership = new CarDealership();
        CarMaker toyota = new CarMaker("Toyota", PRODUCTION_LIMIT, dealership);

        ThreadGroup customersThreads = new ThreadGroup("CustomersThreads");
        new Thread(customersThreads, new Customer("Покупатель1", dealership)).start();
        new Thread(customersThreads, new Customer("Покупатель2", dealership)).start();
        new Thread(customersThreads, new Customer("Покупатель3", dealership)).start();

        Thread toyotaThread = new Thread(toyota);
        toyotaThread.start();
        toyotaThread.join();

        customersThreads.interrupt();
    }

}
