package buzov.task3.matrix.creat;

import java.math.BigDecimal;

public class CreatorMatrix {

    private static final int accuracy = 3;

    public static BigDecimal roundNumber(double value, int digits) {
        //we approximate the transferred number "value" with accuracy "digits"          
        BigDecimal num = new BigDecimal(value).setScale(digits, BigDecimal.ROUND_UP);
        return num;
    }

    public static int[][] makeRandomInt(int rows, int cols) {
        int[][] matrix;
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (int) (Math.random() * 10);   // fill with random values
            }
        }
        return matrix;
    }

    // returns randomized matrix:
    public static double[][] makeRandomDouble(int rows, int cols) {
        double[][] matrix;
        matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // fill with random values
                matrix[i][j] = roundNumber(Math.random() * 10, accuracy).doubleValue();
            }
        }
        return matrix;
    }

}
