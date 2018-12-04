import java.io.*;

public class InputParser {

    private double[][] matrix;
    private double[] vector;
    private BufferedReader reader;

    public InputParser(String fileName) {
        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parse() throws IOException {
        int size = Integer.parseInt(reader.readLine());
        matrix = new double[size][size];
        vector = new double[size];
        for (int i = 0; i < size; i++) {
            String[] cellValues = reader.readLine().split(" ");
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Double.parseDouble(cellValues[j]);
            }
        }
        String[] vectorValues = reader.readLine().split(" ");
        for (int j = 0; j < size; j++) {
            vector[j] = Double.parseDouble(vectorValues[j]);
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getVector() {
        return vector;
    }
}
