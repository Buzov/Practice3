package buzov.task3.matrix;

public class MatrixSelector {

    public Matrix getMatrix(int rows, int cols, DataType dataType) {
        Matrix matrix = null;
        switch (dataType) {
            case DOUBLE:
                matrix = new MatrixDouble(rows, cols);
                break;
            case ARRAY:
                matrix = new MatrixArray(rows, cols);
                break;
        }

        return matrix;
    }

}
