public class Fork implements Comparable<Fork> {

    private int priority;
    private BinSemaphore semaphore;

    public Fork(int priority, BinSemaphore semaphore) {
        this.priority = priority;
        this.semaphore = semaphore;
    }

    public BinSemaphore getSemaphore() {
        return semaphore;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Fork f) {
        return Integer.compare(this.priority, f.getPriority());
    }
}
