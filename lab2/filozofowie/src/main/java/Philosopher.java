import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Philosopher extends Thread {
    private int id;
    private List<Fork> forks;
    private Random randGenerator;

    public Philosopher(int id, List<Fork> forks) {
        this.id = id;
        this.randGenerator = new Random();
        this.forks = forks;
    }

    public void run() {
        try {
            think();
            takeForks();
            eat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        returnForks();
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " thinks");
        Thread.sleep(randGenerator.nextInt(900) + 100);
    }

    private void takeFork(int id) throws InterruptedException {
        Fork fork = forks.get(id);
        fork.getSemaphore().P();
        System.out.println("Philosopher " + this.id + " takes fork " + fork.getPriority());
    }

    private void takeForks() throws InterruptedException {
        if (id < 4) {
            takeFork(id);
            takeFork(id + 1);
        } else {
            takeFork(0);
            takeFork(id);
        }
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " starts eating");
        Thread.sleep(randGenerator.nextInt(1800) + 200);
    }

    private void returnFork(int id) {
        Fork fork = forks.get(id);
        System.out.println("Philosopher " + this.id + " returns fork " + fork.getPriority());
        fork.getSemaphore().V();
    }

    private void returnForks() {
        if (id < 4) {
            returnFork(id);
            returnFork(id + 1);
        } else {
            returnFork(0);
            returnFork(id);
        }
    }
}
