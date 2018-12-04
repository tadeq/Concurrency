public class CalculateCoefsTask implements Runnable {
    private int i;
    private int j;
    private double[][] matrix;
    private double[] coefs;

    public CalculateCoefsTask(int i, int j, double[][] matrix, double[] coefs) {
        this.i = i;
        this.j = j;
        this.matrix = matrix;
        this.coefs = coefs;
    }

    public void run() {
        coefs[i] = matrix[j][i] / matrix[i][i];
    }
}