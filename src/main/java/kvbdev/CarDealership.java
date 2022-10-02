package kvbdev;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarDealership {
    // Использование BlockingQueue сильно бы упростило код и сделало его чище.
    // LinkedBlockingQueue уже построена на ReentrantLock и имеет методы .put() и .take() с ожиданием.
    // Но в задании указано использовать именно ReentrantLock с честной очередью блокировки.
    // private BlockingQueue<Car> queue = new LinkedBlockingQueue<>();

    private Queue<Car> queue = new LinkedList<>();
    private final ReentrantLock locker = new ReentrantLock(true);
    private final Condition condition = locker.newCondition();

    public void accept(Car car) throws InterruptedException {
        locker.lock();
        try {
            queue.add(car);
            condition.signal();
        } finally {
            locker.unlock();
        }
    }

    public boolean isEmpty() {
        locker.lock();
        try {
            return queue.isEmpty();
        } finally {
            locker.unlock();
        }
    }

    public Car get() throws InterruptedException {
        locker.lock();
        try {
            if (isEmpty()) {
                System.out.println("Машин нет");
                condition.await();
            }
            return queue.poll();
        } finally {
            locker.unlock();
        }
    }

}
