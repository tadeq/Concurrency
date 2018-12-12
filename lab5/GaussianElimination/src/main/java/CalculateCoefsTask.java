public class CalculateCoefsTask implements Runnable {

    private int row;
    private int column;
    private double[][] matrix;
    private double[] coefs;

    CalculateCoefsTask(int row, int column, double[][] matrix, double[] coefs) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;
        this.coefs = coefs;
    }

    public void run() {
        coefs[row] = matrix[row][column] / matrix[column][column];
    }
}