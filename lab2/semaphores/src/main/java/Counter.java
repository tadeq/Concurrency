public class Counter {
    private int counter;

    public Counter (int counter){
        this.counter = counter;
    }

    public void increment(){
        this.counter++;
    }

    public void decrement(){
        this.counter--;
    }

    public int getCounter(){
        return this.counter;
    }

}
