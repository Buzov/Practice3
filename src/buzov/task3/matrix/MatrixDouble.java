package buzov.task3.matrix;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;

public class MatrixDouble implements Matrix {

    public double[][] mas;
    private int rows;
    private int cols;
    public static final int stepRow = 1;
    private static volatile AtomicInteger rowForTread = new AtomicInteger(0);

    public MatrixDouble() {
        this.rows = 0;
        this.cols = 0;
        mas = new double[rows][cols];
    }

    public MatrixDouble(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        mas = new double[rows][cols];
    }

    public MatrixDouble(double[][] mas) {
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

    public double[][] getArray() {
        return mas;
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

        int temp;
        temp = rowForTread.get();
        rowForTread.compareAndSet(temp, temp + stepRow);

    }

    public static synchronized void setZeroRowsForTread() {
        int temp;
        temp = rowForTread.get();
        rowForTread.compareAndSet(temp, 0);

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
        System.out.println("Type of data is double[][].");
        System.out.println("Multiplication of matrixes A" + aRows
                + "x" + aCols + " and B" + bRows
                + "x" + bCols + ".");
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
        System.out.println("Multiplication of matrixes lasted " + time + " ms.");
        return C;
    }

    public static MatrixDouble multiply(MatrixDouble mA, MatrixDouble mB) throws IllegalSizesException {

        double[][] A = mA.getArray();
        double[][] B = mB.getArray();

        MatrixDouble C = new MatrixDouble(multiply(A, B));

        return C;
    }

    public static double[][] multiplyTread(double[][] A, double[][] B) {
        int aRows = A.length;
        int aCols = A[0].length;
        int bRows = B.length;
        int bCols = B[0].length;
        if (aCols != bRows) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        double[][] C = new double[aRows][bCols];
        System.out.println("Type of data is double[][].");
        System.out.println("Multitread multiplication of matrixes A" + aRows
                + "x" + aCols + " and B" + bRows
                + "x" + bCols + ".");
        long startTime = System.currentTimeMillis();
        
        int quantityOfStreams;
        if (aRows <= 100) {
            quantityOfStreams = 1;
        } else {
            //The maximum number of processors available to the virtual machine
            quantityOfStreams = Runtime.getRuntime().availableProcessors();
        }

        Thread[] thrd = new Thread[quantityOfStreams];

        for (int i = 0; i < quantityOfStreams; i++) {
            thrd[i] = new Thread(new TreadMultiplyD(A, B, C));
            thrd[i].start(); //thread start

        }
        for (int i = 0; i < quantityOfStreams; i++) {
            try {
                thrd[i].join(); // joining threads
            } catch (InterruptedException e) {
                System.out.println("Exception of tread.");
            }
        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Multiplication of matrixes lasted " + time + " ms.");
        setZeroRowsForTread();
        System.out.println(getRowsForTread());
        return C;
    }

}
