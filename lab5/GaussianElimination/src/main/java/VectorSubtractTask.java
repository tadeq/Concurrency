public class VectorSubtractTask implements Runnable {

    private int subtractedRow;
    private int modifiedRow;
    private double[] vector;
    private double coef;

    VectorSubtractTask(int subtractedRow, int modifiedRow, double coef, double[] vector) {
        this.subtractedRow = subtractedRow;
        this.modifiedRow = modifiedRow;
        this.coef = coef;
        this.vector = vector;
    }

    public void run() {
        vector[modifiedRow] -= coef * vector[subtractedRow];
    }
}
