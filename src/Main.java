import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Main {


    static class CounterIncrease implements Runnable {
        private final Lock lock = new ReentrantLock();
        private final int largestNum;

        public CounterIncrease(int num) {
            this.largestNum = num;
        }

        public void run() {
            lock.lock();
            try {
                for (int i = 1; i <= largestNum; i++) {
                    System.out.println(i);
                }
            } catch (Exception e) {
                Logger.getLogger(e.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

    static class CounterDecrease implements Runnable {
        private final Lock lock = new ReentrantLock();
        private final int largestNum;

        public CounterDecrease(int num) {
            this.largestNum = num;
        }

        public void run() {
            lock.lock();
            try {
                for (int i = largestNum; i > 0; i--) {
                    System.out.println(i);
                }
            } catch (Exception e) {
                Logger.getLogger(e.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        int largestNum = 20;

        CounterIncrease counterUp = new CounterIncrease(largestNum);
        CounterDecrease counterDown = new CounterDecrease(largestNum);
        Thread threadUp = new Thread(counterUp);
        Thread threadDown = new Thread(counterDown);

        threadUp.start();

        try {
            threadUp.join();
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }

        threadDown.start();

    }

}
