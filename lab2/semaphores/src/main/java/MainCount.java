public class MainCount {
    public static void main(String[] args) {
        Semaphore semaphore = new CountSemaphore(4);
        Counter counter = new Counter(0);

        Thread incThread1 = new IncThread(counter,semaphore);
        Thread incThread2 = new IncThread(counter,semaphore);
        Thread incThread3 = new IncThread(counter,semaphore);
        Thread decThread1 = new DecThread(counter,semaphore);
        Thread decThread2 = new DecThread(counter,semaphore);
        incThread1.start();
        incThread2.start();
        incThread3.start();
        decThread1.start();
        decThread2.start();
        try{
            incThread1.join();
            incThread2.join();
            incThread3.join();
            decThread1.join();
            decThread2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
