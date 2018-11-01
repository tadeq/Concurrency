public class DecThread extends Thread {

    private Counter counter;
    private Semaphore semaphore;

    public DecThread(Counter counter, Semaphore semaphore) {
        this.counter = counter;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            try {
                semaphore.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter.decrement();
            System.out.println(counter.getCounter());
            try {
                semaphore.V();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
