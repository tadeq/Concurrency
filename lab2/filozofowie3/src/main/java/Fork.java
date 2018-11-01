public class Fork {

    private boolean clean;
    private int number;
    private BinSemaphore semaphore;

    public Fork(int number, BinSemaphore semaphore) {
        this.clean = false;
        this.number = number;
        this.semaphore = semaphore;
    }

    public BinSemaphore getSemaphore() {
        return semaphore;
    }

    public int getNumber() {
        return number;
    }


    public boolean isClean() {
        return clean;
    }

    public void use(){
        clean = false;
    }

    public void clean(){
        clean = true;
    }

}
