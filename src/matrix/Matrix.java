/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import matrix.err.MatrixIndexOutOfBoundsException;

/**
 *
 * @author RT
 */
public interface Matrix {

    int getColsCount();

    int getRowsCount();

    int getSize();

    double getValue(int row, int col) throws MatrixIndexOutOfBoundsException;

    void setValue(int row, int col, double value) throws MatrixIndexOutOfBoundsException;

    void print();

}
