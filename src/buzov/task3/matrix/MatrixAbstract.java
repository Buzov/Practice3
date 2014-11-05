package buzov.task3.matrix;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class MatrixAbstract implements Matrix {

    protected int rows;
    protected int cols;
    public static final int stepRow = 1;
    protected static /*volatile*/ AtomicInteger rowForTread = new AtomicInteger(0);
    Object dataObject;

    public MatrixAbstract(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    protected MatrixAbstract(Object object) {
        dataObject = object;
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

    public static synchronized int getRowsForThread() {
        int temp = rowForTread.get();
        return temp;
    }

    public static synchronized int getRowsForThreadWithInkrement() {
        int temp = rowForTread.get();
        setRowsForThread(stepRow);
        return temp;
    }

    public static synchronized void setRowsForThread(int stepRow) {
        int temp;
        temp = rowForTread.get();
        rowForTread.compareAndSet(temp, temp + stepRow);
    }

    public static synchronized void setZeroRowsForThread() {
        int temp;
        temp = rowForTread.get();
        rowForTread.compareAndSet(temp, 0);
    }

}
