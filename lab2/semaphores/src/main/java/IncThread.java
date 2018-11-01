public class IncThread extends Thread {

    private Counter counter;
    private Semaphore semaphore;

    public IncThread(Counter counter, Semaphore semaphore) {
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
            counter.increment();
            System.out.println(counter.getCounter());
            try {
                semaphore.V();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
