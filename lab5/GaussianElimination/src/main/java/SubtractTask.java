public class SubtractTask implements Runnable {
    private int i;
    private int j;
    private int size;
    private double[] coefs;
    private double[][] matrix;
    private double[] vector;

    public SubtractTask(int i, int j, double[] coefs, double[][] matrix, int size, double[] vector) {
        this.i = i;
        this.j = j;
        this.size = size;
        this.coefs = coefs;
        this.matrix = matrix;
        this.vector = vector;
    }

    public void run() {
        for (int col = 0; col < size; col++) {
            matrix[j][col] -= coefs[j] * matrix[i][col];
        }
        vector[j] -= coefs[j] * vector[i];
    }
}