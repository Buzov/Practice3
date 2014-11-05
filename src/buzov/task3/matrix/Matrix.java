package buzov.task3.matrix;

import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.IncorrectFormatDfData;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;

/**
 *
 * @author Artur Buzov
 */
public interface Matrix {

    int getColsCount();

    int getRowsCount();

    int getSize();

    Object getArray();

    double getValue(int row, int col) throws MatrixIndexOutOfBoundsException;

    void setValue(int row, int col, double value) throws MatrixIndexOutOfBoundsException;

    void print();
    
    Matrix read(String path);

    void write(String path);
    
    void initialize();

    Matrix multiply(Matrix B) throws IllegalSizesException, IncorrectFormatDfData;

    Matrix multiplyThread(Matrix B) throws IllegalSizesException, IncorrectFormatDfData;

}
