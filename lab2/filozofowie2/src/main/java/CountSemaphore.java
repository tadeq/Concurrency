public class CountSemaphore implements Semaphore {

    private int val;
    private BinSemaphore semaphore;
    private BinSemaphore valSemaphore;

    public CountSemaphore(int value) {
        this.val = value;
        semaphore = new BinSemaphore(val > 0);
        valSemaphore = new BinSemaphore(true);
    }

    public synchronized void P() throws InterruptedException {
        valSemaphore.P();
        if (val > 0) {
            val--;
            if (val == 0)
                semaphore.P();
        }
        valSemaphore.V();
    }

    public synchronized void V() throws InterruptedException {
        valSemaphore.P();
        val++;
        if (val == 1)
            semaphore.V();
        valSemaphore.V();
    }
}
