package matrix;

import java.util.ArrayList;
import matrix.err.MatrixIndexOutOfBoundsException;

public class Matrix {
  
  private double[][] mas;
  private int rows;
  private int cols;
  private ArrayList <ArrayList<Double>> matrix;
  
  public Matrix() {
    this.rows = 0;
    this.cols = 0;
    mas = new double[rows][cols];
  }
  
  public Matrix(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    mas = new double[rows][cols];
  }
  
  public Matrix(double[][] mas) {
    this.rows = mas.length;
    this.cols = mas[0].length;
    this.mas = mas;
  }
  
  public int colsCount() {
    return cols;
  }
  
  public int rowsCount() {
    return rows;
  }
  
  public int size() {
    return cols * rows;
  }
  
  public double getValue(int row, int col) 
          throws MatrixIndexOutOfBoundsException {
    if ( (row < 0) || (col < 0) || (row >= mas.length) || (col >= mas[0].length) )
      throw new MatrixIndexOutOfBoundsException();
    return mas[row][col];
  }
  
  public void setValue(int row, int col, double value) 
          throws MatrixIndexOutOfBoundsException {
    if ( (row < 0) || (col < 0) || (row >= mas.length) || (col >= mas[0].length) )
      throw new MatrixIndexOutOfBoundsException();
    mas[row][col] = value;
  }
  
  public double[][] toArray() {
    return mas;
  }
  
  public void print() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.printf("%8.3f ", mas[i][j]);
      }
      System.out.println();
    }
  }
  
}
