public class Fork {

    private int number;
    private BinSemaphore semaphore;

    public Fork(int number, BinSemaphore semaphore) {
        this.number = number;
        this.semaphore = semaphore;
    }

    public BinSemaphore getSemaphore() {
        return semaphore;
    }

    public int getNumber() {
        return number;
    }
}
