import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        InputParser parser = new InputParser("input10.txt");
        parser.parse();
        double[][] matrix = parser.getMatrix();
        double[] vector = parser.getVector();
        int size = matrix.length;
        double[] coefs;
        Thread[] threads;
        for (int column = 0; column < size; column++) {
            coefs = new double[size];
            threads = new Thread[size];

            for (int row = 0; row < size; row++)
                if (row != column)
                    threads[row] = new Thread(new CalculateCoefsTask(row, column, matrix, coefs));

            for (int row = 0; row < size; row++)
                if (row != column)
                    threads[row].start();

            for (int row = 0; row < size; row++)
                if (row != column)
                    threads[row].join();

            Thread[][] matrixThreads = new Thread[size][size];
            threads = new Thread[size];

            for (int row = 0; row < size; row++) {
                if (row != column) {
                    for (int col = 0; col < size; col++)
                        matrixThreads[row][col] = new Thread(new MatrixSubtractTask(column, row, col, matrix, coefs[row]));
                    threads[row] = new Thread(new VectorSubtractTask(column, row, coefs[row], vector));
                }
            }

            for (int row = 0; row < size; row++) {
                if (row != column) {
                    for (int col = 0; col < size; col++)
                        matrixThreads[row][col].start();
                    threads[row].start();
                }
            }
            for (int row = 0; row < size; row++) {
                if (row != column) {
                    for (int col = 0; col < size; col++)
                        matrixThreads[row][col].join();
                    threads[row].join();
                }
            }
        }

        threads = new Thread[size];
        for (int column = 0; column < size; column++)
            threads[column] = new Thread(new ReduceTask(column, matrix, vector));

        for (int column = 0; column < size; column++)
            threads[column].start();

        for (int column = 0; column < size; column++)
            threads[column].join();

        PrintStream printStream = new PrintStream(new FileOutputStream(new File("output10.txt")));
        System.setOut(printStream);
        System.out.println(size);

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if ((Math.abs(matrix[row][column])) < 0.000001)
                    matrix[row][column] = 0.0;
            }
        }
        for (int column = 0; column < size; column++) {
            if ((Math.abs(vector[column])) < 0.000001)
                vector[column] = 0.0;
        }

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.print(matrix[row][column] + " ");
            }
            System.out.println();
        }
        for (int column = 0; column < size; column++) {
            System.out.print(vector[column] + " ");
        }
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}