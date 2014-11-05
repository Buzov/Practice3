package buzov.task3.matrix;

import buzov.task3.matrix.creat.InitsializatorMatrix;
import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.IncorrectFormatDfData;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import buzov.task3.matrix.input.ReaderMatrix;
import buzov.task3.matrix.operations.MultiplierMatrix;
import buzov.task3.matrix.otput.WriterMatrix;
import java.util.ArrayList;

public class MatrixArray extends MatrixAbstract {

    private ArrayList<ArrayList<Double>> mas;

    public MatrixArray(int rows, int cols) {
        super(rows, cols);
        mas = new ArrayList<>();
    }

    public MatrixArray(Object object) {
        super(object);
        mas = (ArrayList<ArrayList<Double>>) dataObject;
        this.rows = mas.size();
        this.cols = mas.get(0).size();
    }

    @Override
    public double getValue(int row, int col) throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.size()) || (col >= mas.get(0).size())) {
            throw new MatrixIndexOutOfBoundsException("Inadmissible value of an index.");
        }
        return mas.get(row).get(col);
    }

    @Override
    public void setValue(int row, int col, double value) throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.size()) || (col >= mas.get(0).size())) {
            throw new MatrixIndexOutOfBoundsException("Inadmissible value of an index.");
        }
        mas.get(row).set(col, value);
    }

    @Override
    public Object getArray() {
        return (Object) mas;
    }

    @Override
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8.3f ", mas.get(i).get(j));
            }
            System.out.println();
        }
    }

    @Override
    public Matrix read(String path) {
        Matrix matrix = null;

        try {
            matrix = ReaderMatrix.readFromFile(path, DataType.ARRAY);
        } catch (MatrixIndexOutOfBoundsException ex) {
            System.out.println(ex);;
        }
        return matrix;
    }

    @Override
    public void write(String path) {
        try {
            WriterMatrix.write(this, path, DataType.ARRAY);
        } catch (MatrixIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    @Override
    public Matrix multiply(Matrix B) throws IllegalSizesException, IncorrectFormatDfData {

        return MultiplierMatrix.multiply(this, B, DataType.ARRAY);
    }

    @Override
    public Matrix multiplyThread(Matrix B) throws IncorrectFormatDfData, IllegalSizesException {

        return MultiplierMatrix.multiplyThread(this, B, DataType.ARRAY);
    }

    @Override
    public void initialize() {
        InitsializatorMatrix.makeRandom(this, DataType.DOUBLE);
    }

}
