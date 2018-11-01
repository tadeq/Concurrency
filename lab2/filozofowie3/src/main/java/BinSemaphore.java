public class BinSemaphore {
    private boolean free;
    public BinSemaphore(boolean free) {
        this.free = free;
    }

    public synchronized void P() throws InterruptedException {
        while (!free) wait();
        free = false;
    }

    public synchronized void V() {
        if (!free) {
            free = true;
            notifyAll();
        }
    }

    public boolean isFree() {
        return free;
    }

}
