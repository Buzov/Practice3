package buzov.task3.matrix;

import java.util.Random;
import buzov.task3.matrix.err.MatrixIndexOutOfBoundsException;
import buzov.task3.matrix.err.WrongRangeException;

public class CreatorMatrix {

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
                matrix[i][j] = Math.random() * 10;   // fill with random values
            }
        }
        return matrix;
    }

    public static void fromRandom(MatrixD m, int min, int max) throws MatrixIndexOutOfBoundsException,
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

    public static void fromRandom(MatrixD m, double min, double max) throws MatrixIndexOutOfBoundsException,
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

    public static void fromRandom(MatrixD m, int max) throws MatrixIndexOutOfBoundsException,
                                                            WrongRangeException {
        fromRandom(m, 0, max);
    }

    public static void fromRandom(MatrixD m, double max) throws MatrixIndexOutOfBoundsException,
                                                               WrongRangeException {
        fromRandom(m, 0.0, max);
    }

}
