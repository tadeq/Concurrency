import java.util.List;
import java.util.Random;

public class Philosopher extends Thread {
    private int id;
    private List<Fork> forks;
    private Random randGenerator;

    public Philosopher(int id, List<Fork> forks) {
        this.id = id;
        this.forks = forks;
        this.randGenerator = new Random();
    }

    public void run() {
        try {
            think();
            takeFork(id);
            takeFork((id + 1) % 5);
            eat();
            returnFork((id+1)%5);
            returnFork(id);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " thinks");
        Thread.sleep(randGenerator.nextInt(900) + 100);
    }

    public synchronized void takeFork(int id) throws InterruptedException{
        Fork fork = forks.get(id);
        while (fork.isClean() && !fork.getSemaphore().isFree())
            wait();
        fork.getSemaphore().P();
        System.out.println("Philosopher " + this.id + " takes fork " + fork.getNumber());
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " starts eating");
        forks.get(id).use();
        forks.get((id+1)%5).use();
        Thread.sleep(randGenerator.nextInt(1800) + 200);
    }

    private synchronized void returnFork(int id) {
        Fork fork = forks.get(id);
        fork.clean();
        notifyAll();
        System.out.println("Philosopher " + this.id + " returns fork " + fork.getNumber());
        fork.getSemaphore().V();
    }
}
