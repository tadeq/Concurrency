import java.util.List;
import java.util.Random;

public class Philosopher extends Thread {
    private int id;
    private List<Fork> forks;
    private Semaphore tableSlots;
    private Random randGenerator;

    public Philosopher(int id, List<Fork> forks, Semaphore tableSlots) {
        this.id = id;
        this.forks = forks;
        this.tableSlots = tableSlots;
        this.randGenerator = new Random();
    }

    public void run() {
        try {
            think();
            tableSlots.P();
            takeFork(id);
            takeFork((id + 1) % 5);
            eat();
            returnFork((id+1)%5);
            returnFork(id);
            tableSlots.V();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " thinks");
        Thread.sleep(randGenerator.nextInt(900) + 100);
    }

    public void takeFork(int id) throws InterruptedException{
        Fork fork = forks.get(id);
        fork.getSemaphore().P();
        System.out.println("Philosopher " + this.id + " takes fork " + fork.getNumber());
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " starts eating");
        Thread.sleep(randGenerator.nextInt(1800) + 200);
    }

    private void returnFork(int id) {
        Fork fork = forks.get(id);
        System.out.println("Philosopher " + this.id + " returns fork " + fork.getNumber());
        fork.getSemaphore().V();
    }
}
