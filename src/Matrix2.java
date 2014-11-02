

import matrix.*;
import java.util.ArrayList;
import matrix.err.IllegalSizesException;
import matrix.err.MatrixIndexOutOfBoundsException;

public class Matrix2{

    private double[][] mas;
    private int rows;
    private int cols;
    private ArrayList<ArrayList<Double>> matrix;

    public Matrix2() {
        this.rows = 0;
        this.cols = 0;
        mas = new double[rows][cols];
    }

    public Matrix2(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        mas = new double[rows][cols];
    }

    public Matrix2(double[][] mas) {
        this.rows = mas.length;
        this.cols = mas[0].length;
        this.mas = mas;
    }

    public int getColsCount() {
        return cols;
    }

    public int getRowsCount() {
        return rows;
    }

    public int size() {
        return cols * rows;
    }

    public double getValue(int row, int col)
            throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.length) || (col >= mas[0].length)) {
            throw new MatrixIndexOutOfBoundsException();
        }
        return mas[row][col];
    }

    public void setValue(int row, int col, double value)
            throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.length) || (col >= mas[0].length)) {
            throw new MatrixIndexOutOfBoundsException();
        }
        mas[row][col] = value;
    }

    public double[][] getArray() {
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
    
    public static void print(double[][] A) {
        int aRows = A.length;
        int aCols = A[0].length;
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aCols; j++) {
                System.out.printf("%8.3f ", A[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void print(ArrayList<ArrayList<Double>> A) {
        int aRows = A.size();
        int aCols = A.get(0).size();
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aCols; j++) {
                System.out.printf("%8.3f ", A.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        int aRows = A.length;
        int aCols = A[0].length;
        int bRows = B.length;
        int bCols = A[0].length;
        if (aCols != bRows) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        
        System.out.println("Перемножение матриц размерами " + aCols
                + " столбцов и " + aRows + " строк и " + bCols
                + " столбцов и " + bRows + " строк");
        long startTime = System.currentTimeMillis();
        
        double[][] C = new double[aRows][bCols];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    C[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Перемножение матриц выполнено за " + time + " мс");
        return C;
    }

    public static Matrix2 multiply(Matrix2 mA, Matrix2 mB) throws IllegalSizesException {
        int aRows = mA.getRowsCount();
        int aCols = mA.getColsCount();
        int bRows = mB.getRowsCount();
        int bCols = mB.getColsCount();
        double[][] A = mA.getArray();
        double[][] B = mB.getArray();

        if (aCols != bRows) {
            throw new IllegalSizesException();
        }

        long startTime = System.currentTimeMillis();
        System.out.println("Перемножение матриц размерами " + aCols
                + " столбцов и " + aRows + " строк и " + bCols 
                + " столбцов и " + bRows + " строк");

        double[][] R = new double[aRows][bCols];
        double currentCol[] = new double[bRows];

        for (int j = 0; j < bCols; j++) {
            for (int k = 0; k < aCols; k++) {
                currentCol[k] = B[k][j];
            }
            for (int i = 0; i < aRows; i++) {
                double currentRow[] = A[i];
                double t = 0.0;
                for (int k = 0; k < aCols; k++) {
                    t += currentRow[k] * currentCol[k];
                }
                R[i][j] = t;
            }
        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Перемножение матриц выполнено за " + time + " мс");
        Matrix2 mR = new Matrix2(R);
        return mR;
    }

}
