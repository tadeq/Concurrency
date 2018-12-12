public class ReduceTask implements Runnable {

    private int row;
    private double[][] matrix;
    private double[] vector;

    ReduceTask(int row, double[][] matrix, double[] vector) {
        this.row = row;
        this.matrix = matrix;
        this.vector = vector;
    }

    public void run() {
        vector[row] /= matrix[row][row];
        matrix[row][row] = 1;
    }
}