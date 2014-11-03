package buzov.task3.matrix;

import static buzov.task3.matrix.MatrixDouble.setZeroRowsForTread;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;

public class MatrixArray implements Matrix {

    private int rows;
    private int cols;
    private ArrayList<ArrayList<Double>> mas;
    public static final int stepRow = 1;
    private static volatile AtomicInteger rowForTread = new AtomicInteger(0);

    public MatrixArray() {
        this.rows = 0;
        this.cols = 0;
        mas = new ArrayList<>();
    }

    public MatrixArray(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        mas = new ArrayList<>();
    }

    public MatrixArray(ArrayList<ArrayList<Double>> mas) {
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

    public ArrayList<ArrayList<Double>> getArray() {
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

    public static MatrixArray multiply(MatrixArray mA, MatrixArray mB) throws IllegalSizesException {

        ArrayList<ArrayList<Double>> A = mA.getArray();
        ArrayList<ArrayList<Double>> B = mB.getArray();

        MatrixArray C = new MatrixArray(multiply(A, B));

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

        int quantityOfStreams;
        if (aRows <= 100) {
            quantityOfStreams = 1;
        } else {
            //The maximum number of processors available to the virtual machine
            quantityOfStreams = Runtime.getRuntime().availableProcessors();
        }

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
        setZeroRowsForTread();
        System.out.println(getRowsForTread());
        return C;
    }

}
