package buzov.task3.matrix;

import java.util.Random;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import buzov.task3.matrix.exception.WrongRangeException;
import java.math.BigDecimal;

public class CreatorMatrix {

    private static final int accuracy= 3;
    public static BigDecimal roundNumber(double value, int digits) {

        //we approximate the transferred number "value" with accuracy "digits"          
        BigDecimal num = new BigDecimal("" + value).
                setScale(digits, BigDecimal.ROUND_HALF_UP);
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
    public static double[][] makeRandom(int rows, int cols) {
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

    public static void makeRandomMatrixDouble(MatrixDouble m, int min, int max) throws MatrixIndexOutOfBoundsException,
                                                                     WrongRangeException {
        int cols = m.getColsCount();
        int rows = m.getRowsCount();

        if (max < min) {
            throw new WrongRangeException();
        }

        Random r = new Random();
        int next;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                next = r.nextInt(Math.abs(min - max) + 1) + min;
                m.setValue(i, j, next);
            }
        }
    }

    public static void makeRandomMatrixDouble(MatrixDouble m, double min, double max) throws MatrixIndexOutOfBoundsException,
                                                                           WrongRangeException {
        int cols = m.getColsCount();
        int rows = m.getRowsCount();

        if (max < min) {
            throw new WrongRangeException();
        }

        Random r = new Random();
        double next;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                next = min + (max - min) * r.nextDouble();
                m.setValue(i, j, next);
            }
        }
    }

    public static void makeRandomMatrixDouble(MatrixDouble m, int max) throws MatrixIndexOutOfBoundsException,
                                                            WrongRangeException {
        CreatorMatrix.makeRandomMatrixDouble(m, 0, max);
    }

    public static void makeRandomMatrixDouble(MatrixDouble m, double max) throws MatrixIndexOutOfBoundsException,
                                                               WrongRangeException {
        CreatorMatrix.makeRandomMatrixDouble(m, 0.0, max);
    }

}
