import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        InputParser parser = new InputParser("input.txt");
        parser.parse();
        double[][] matrix = parser.getMatrix();
        double[] vector = parser.getVector();
        int size = matrix.length;
        Thread[] threads;
        double[] coefs;
        for (int i = 0; i < size; i++) {
            coefs = new double[size];
            threads = new Thread[size];

            for (int j = 0; j < size && j != i; j++) {
                threads[j] = new Thread(new CalculateCoefsTask(i, j, matrix, coefs));
            }

            for (int j = 0; j < size && j != i; j++) {
                threads[j].start();
            }

            for (int j = 0; j < size && j != i; j++) {
                threads[j].join();
            }
            threads = new Thread[size];
            for (int j = 0; j < size && j != i; j++) {
                threads[j] = new Thread(new SubtractTask(i, j, coefs, matrix, size, vector));
            }

            for (int j = 0; j < size && j != i; j++) {
                threads[j].start();
            }

            for (int j = 0; j < size && j != i; j++) {
                threads[j].join();
            }
        }
        threads = new Thread[size];
        for (int j = 0; j < size; j++) {
            threads[j] = new Thread(new ReduceTask(j, matrix, vector));
        }

        for (int j = 0; j < size; j++) {
            threads[j].start();
        }

        for (int j = 0; j < size; j++) {
            threads[j].join();
        }

        PrintStream printStream = new PrintStream(new FileOutputStream(new File("output.txt")));
        System.setOut(printStream);
        System.out.println(size);
        for (double[] row : matrix) {
            for (int j = 0; j < size; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
        for (int j = 0; j < size; j++) {
            System.out.print(vector[j] + " ");
        }
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}