package buzov.task3.matrix.creat;

import buzov.task3.matrix.MatrixDouble;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import buzov.task3.matrix.exception.WrongRangeException;
import java.util.Random;

public class InitsializatorMatrix {

    public static void makeRandomDouble(MatrixDouble m, int min, int max) throws WrongRangeException,
                                                                                       MatrixIndexOutOfBoundsException {
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

    public static void makeRandomDouble(MatrixDouble m, double min, double max) throws WrongRangeException,
                                                                                             MatrixIndexOutOfBoundsException {
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

    public static void makeRandomDouble(MatrixDouble m, int max) throws WrongRangeException,
                                                                              MatrixIndexOutOfBoundsException {
        InitsializatorMatrix.makeRandomDouble(m, 0, max);
    }

    public static void makeRandomDouble(MatrixDouble m, double max) throws WrongRangeException,
                                                                                 MatrixIndexOutOfBoundsException {
        InitsializatorMatrix.makeRandomDouble(m, 0.0, max);
    }
}
