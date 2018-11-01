public class MainBin {
    public static void main(String[] args) {
        Semaphore binSemaphore = new BinSemaphore(true);
        Counter counter = new Counter(0);

        Thread incThread = new IncThread(counter,binSemaphore);
        Thread decThread = new DecThread(counter,binSemaphore);
        incThread.start();
        decThread.start();
        try{
            incThread.join();
            decThread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
