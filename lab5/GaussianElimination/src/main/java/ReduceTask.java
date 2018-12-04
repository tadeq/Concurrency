public class ReduceTask implements Runnable {
    private int i;
    private double[][] matrix;
    private double[] vector;

    public ReduceTask(int i, double[][] matrix, double[] vector) {
        this.i = i;
        this.matrix = matrix;
        this.vector = vector;
    }

    public void run() {
        vector[i] = vector[i] / matrix[i][i];
        matrix[i][i] = 1;
    }
}