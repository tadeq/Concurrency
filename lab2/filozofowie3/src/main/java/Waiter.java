import java.util.ArrayList;
import java.util.List;

public class Waiter {
    public static void main(String[] args) {
        List<Fork> forks = new ArrayList<>();
        List<Philosopher> philosophers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            forks.add(new Fork(i, new BinSemaphore(true)));
        }
        for (int i = 0; i < 5; i++) {
            philosophers.add(new Philosopher(i, forks));
        }
        for (int i = 0; i < 5; i++) {
            philosophers.get(i).start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                philosophers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
