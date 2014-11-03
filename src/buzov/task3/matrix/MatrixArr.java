package buzov.task3.matrix;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import buzov.task3.matrix.err.IllegalSizesException;
import buzov.task3.matrix.err.MatrixIndexOutOfBoundsException;

public class MatrixArr implements Matrix {

    private int rows;
    private int cols;
    private ArrayList<ArrayList<Double>> mas;
    public static final int stepRow = 1;
    private static volatile AtomicInteger rowForTread = new AtomicInteger(0); /*volatile */


    public MatrixArr() {
        this.rows = 0;
        this.cols = 0;
        mas = new ArrayList<>();
    }

    public MatrixArr(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        mas = new ArrayList<>();;
    }

    public MatrixArr(ArrayList<ArrayList<Double>> mas) {
        this.rows = mas.size();
        this.cols = mas.get(0).size();
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
        if ((row < 0) || (col < 0) || (row >= mas.size()) || (col >= mas.get(0).size())) {
            throw new MatrixIndexOutOfBoundsException();
        }
        return mas.get(row).get(col);
    }

    @Override
    public void setValue(int row, int col, double value) throws MatrixIndexOutOfBoundsException {
        if ((row < 0) || (col < 0) || (row >= mas.size()) || (col >= mas.get(0).size())) {
            throw new MatrixIndexOutOfBoundsException();
        }
        mas.get(row).set(col, value);
    }

    public ArrayList<ArrayList<Double>> getArray() {
        return mas;
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

    public static ArrayList<ArrayList<Double>> multiply(ArrayList<ArrayList<Double>> A,
                                                        ArrayList<ArrayList<Double>> B) {
        int aRows = A.size();
        int aCols = A.get(0).size();
        int bRows = B.size();
        int bCols = B.get(0).size();
        if (aCols != bRows) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }

        System.out.println("Type of data is ArrayList<ArrayList<Double>>.");
        System.out.println("Multiplication of matrixes A" + aRows
                + "x" + aCols + " and B" + bRows
                + "x" + bCols + ".");
        ArrayList<ArrayList<Double>> C = new ArrayList<>();

        for (int i = 0; i < aRows; i++) {
            C.add(new ArrayList<Double>());
            for (int j = 0; j < bCols; j++) {
                C.get(i).add(0d);
            }
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                double temp = 0;
                for (int k = 0; k < aCols; k++) {
                    temp += A.get(i).get(k) * B.get(k).get(j);
                }
                C.get(i).set(j, temp);
            }
        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Multiplication of matrixes lasted " + time + " ms.");
        return C;
    }

    public static MatrixArr multiply(MatrixArr mA, MatrixArr mB) throws IllegalSizesException {

        ArrayList<ArrayList<Double>> A = mA.getArray();
        ArrayList<ArrayList<Double>> B = mB.getArray();

        MatrixArr C = new MatrixArr(multiply(A, B));

        return C;
    }

    public static ArrayList<ArrayList<Double>> multiplyTread(ArrayList<ArrayList<Double>> A,
                                                             ArrayList<ArrayList<Double>> B) {
        int aRows = A.size();
        int aCols = A.get(0).size();
        int bRows = B.size();
        int bCols = B.get(0).size();
        if (aCols != bRows) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }

        System.out.println("Type of data is ArrayList<ArrayList<Double>>.");
        System.out.println("Multitread multiplication of matrixes A " + aRows
                + "x" + aCols + " and B" + bRows
                + "x" + bCols + ".");
        ArrayList<ArrayList<Double>> C = new ArrayList<>();

        for (int i = 0; i < aRows; i++) {
            C.add(new ArrayList<Double>());
            for (int j = 0; j < bCols; j++) {
                C.get(i).add(0d);
            }
        }

        long startTime = System.currentTimeMillis();

        int quantityOfStreams = 2;

        Thread[] thrd = new Thread[quantityOfStreams];

        for (int i = 0; i < quantityOfStreams; i++) {
            thrd[i] = new Thread(new TreadMultiplyArr(A, B, C));
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
