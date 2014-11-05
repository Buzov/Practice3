package buzov.task3.matrix;

import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import buzov.task3.matrix.input.ReaderMatrix;
import buzov.task3.matrix.operations.MultiplierMatrix;
import buzov.task3.matrix.otput.WriterMatrix;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrixDouble extends MatrixAbstract {

    public double[][] mas;

    public MatrixDouble(int rows, int cols) {
        super(rows, cols);
        mas = new double[rows][cols];
    }

    public MatrixDouble(Object object) {
        super(object);
        this.mas = (double[][]) object;
        this.rows = mas.length;
        this.cols = mas[0].length;
    }

    @Override
    public double getValue(int row, int col) throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.length) || (col >= mas[0].length)) {
            throw new MatrixIndexOutOfBoundsException("Inadmissible value of an index.");
        }
        return mas[row][col];
    }

    @Override
    public void setValue(int row, int col, double value) throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.length) || (col >= mas[0].length)) {
            throw new MatrixIndexOutOfBoundsException("Inadmissible value of an index.");
        }
        mas[row][col] = value;
    }

    @Override
    public Object getArray() {
        return (Object) mas;
    }

    @Override
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8.3f ", mas[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public Matrix read(String path) {
        Matrix matrix = null;

        try {
            matrix = ReaderMatrix.readFromFile(path, DataType.DOUBLE);
        } catch (MatrixIndexOutOfBoundsException ex) {
            System.out.println(ex);;
        }
        return matrix;
    }

    @Override
    public void write(String path) {
        try {
            WriterMatrix.write(this, path, DataType.DOUBLE);
        } catch (MatrixIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    @Override
    public Matrix multiply(Matrix A, Matrix B) throws IllegalSizesException {

        return MultiplierMatrix.multiply(A, B, DataType.DOUBLE);
    }

    @Override
    public Matrix multiplyThread(Matrix A, Matrix B) {

        return MultiplierMatrix.multiplyThread(A, B, DataType.DOUBLE);
    }

}
