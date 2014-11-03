package buzov.task3.matrix;

import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;

/**
 *
 * @author Artur Buzov
 */
public interface Matrix {

    int getColsCount();

    int getRowsCount();

    int getSize();

    double getValue(int row, int col) throws MatrixIndexOutOfBoundsException;

    void setValue(int row, int col, double value) throws MatrixIndexOutOfBoundsException;

    void print();
        
}
