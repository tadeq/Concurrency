public class MatrixSubtractTask implements Runnable {

    private int subtractedRow;
    private int modifiedRow;
    private int column;
    private double[][] matrix;
    private double coef;

    MatrixSubtractTask(int subtractedRow, int modifiedRow, int column, double[][] matrix, double coef) {
        this.subtractedRow = subtractedRow;
        this.modifiedRow = modifiedRow;
        this.matrix = matrix;
        this.column = column;
        this.coef = coef;
    }

    public void run() {
        matrix[modifiedRow][column] -= coef * matrix[subtractedRow][column];
    }
}