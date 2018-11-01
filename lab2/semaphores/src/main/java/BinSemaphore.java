public class BinSemaphore implements Semaphore{
    /* Ad 2 - Nie wystarczy tylko instrukcja if, ponieważ wówczas w momencie otrzymania sygnału o zwolnieniu zasobów ruszą wszystkie wątki czekające, a powinien jeden */
    private boolean free;

    public BinSemaphore(boolean free){
        this.free = free;
    }

    public synchronized void P() throws InterruptedException {
        while (!free) wait();
        free = false;
    }

    public synchronized void V(){
        if(!free) {
            free = true;
            notifyAll();
        }
    }

}
