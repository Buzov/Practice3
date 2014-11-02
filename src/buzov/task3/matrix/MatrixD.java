package buzov.task3.matrix;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import buzov.task3.matrix.err.IllegalSizesException;
import buzov.task3.matrix.err.MatrixIndexOutOfBoundsException;

public class MatrixD implements Matrix {

    public double[][] mas;
    private int rows;
    private int cols;
    public static final int stepRow = 1;
    private static volatile AtomicInteger rowForTread = new AtomicInteger(0); /*volatile */


    public MatrixD() {
        this.rows = 0;
        this.cols = 0;
        mas = new double[rows][cols];
    }

    public MatrixD(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        mas = new double[rows][cols];
    }

    public MatrixD(double[][] mas) {
        this.rows = mas.length;
        this.cols = mas[0].length;
        this.mas = mas;
    }

    @Override
    public int getColsCount() {
        return cols;
    }

    @Override
    public int getRowsCount() {
        return rows;
    }

    @Override
    public int getSize() {
        return cols * rows;
    }

    @Override
    public double getValue(int row, int col) throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.length) || (col >= mas[0].length)) {
            throw new MatrixIndexOutOfBoundsException();
        }
        return mas[row][col];
    }

    @Override
    public void setValue(int row, int col, double value) throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.length) || (col >= mas[0].length)) {
            throw new MatrixIndexOutOfBoundsException();
        }
        mas[row][col] = value;
    }

    public double[][] getArray() {
        return mas;
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
        int bCols = B[0].length;
        if (aCols != bRows) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }

        System.out.println("Перемножение матриц размерами " + aRows
                + " столбцов и " + aCols + " строк и " + bRows
                + " столбцов и " + bCols + " строк");
        long startTime = System.currentTimeMillis();

        double[][] C = new double[aRows][bCols];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    System.out.print("*" + B[k][j] + "+");
                    C[i][j] += (A[i][k] * B[k][j]);
                }
                System.out.println();
            }
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Перемножение матриц выполнено за " + time + " мс");
        return C;
    }

    public static MatrixD multiply(MatrixD mA, MatrixD mB) throws IllegalSizesException {
        System.out.println("проблема здесь");
        
        double[][] A = mA.getArray();
        double[][] B = mB.getArray();

        MatrixD C = new MatrixD(multiply(A, B));

        return C;
    }

    public static double[][] multiplyTread(double[][] A, double[][] B) {
        int aRows = A.length;
        int aCols = A[0].length;
        int bRows = B.length;
        int bCols = A[0].length;
        if (aCols != bRows) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double[][] C = new double[aRows][bCols];

        System.out.println("Перемножение матриц размерами " + aCols
                + " столбцов и " + aRows + " строк и " + bCols
                + " столбцов и " + bRows + " строк");
        long startTime = System.currentTimeMillis();

        int NUM_OF_THREADS = 4;

        Thread[] thrd = new Thread[NUM_OF_THREADS];

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            thrd[i] = new Thread(new TreadMultiplyD(A, B, C));
            thrd[i].start(); //thread start

        }
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            try {
                thrd[i].join(); // joining threads
            } catch (InterruptedException e) {
                System.out.println("Проблема с потоками");
            }
        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Перемножение матриц выполнено за " + time + " мс");
        setRowsForTread(0);
        System.out.println(getRowsForTread());
        return C;
    }

    public static synchronized int getRowsForTread() {
        int temp = rowForTread.get();
        return temp;

    }

    public static synchronized int getRowsForTreadWithInkrement() {
        int temp = rowForTread.get();
        setRowsForTread(stepRow);
        return temp;

    }

    public static synchronized void setRowsForTread(int stepRow) {

        if (stepRow == 0) {
            int temp;
            temp = rowForTread.get();
            rowForTread.compareAndSet(temp, 0);
        } else {
            int temp;
            temp = rowForTread.get();
            rowForTread.compareAndSet(temp, temp + stepRow);
        }

    }

}
