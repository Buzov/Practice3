package buzov.task3.matrix;

import java.util.ArrayList;

public class TreadMultiplyArr implements Runnable {

    private boolean stopTread = false;
    private int numderTread = 0;
    private static int sumTread = 0;
    private int rowTotal;
    private int aRows;
    private int aCols;
    private int bCols;
    private ArrayList<ArrayList<Double>> A;
    private ArrayList<ArrayList<Double>> B;
    private ArrayList<ArrayList<Double>> C;

    public TreadMultiplyArr(ArrayList<ArrayList<Double>> A,
                            ArrayList<ArrayList<Double>> B,
                            ArrayList<ArrayList<Double>> C) {
        rowTotal = A.size();
        this.aCols = A.get(0).size();
        this.bCols = B.get(0).size();
        this.A = A;
        this.B = B;
        this.C = C;
        sumTread++;
        numderTread = sumTread;
    }

    public TreadMultiplyArr(MatrixArray A, MatrixArray B, MatrixArray C) {
        rowTotal = A.getRowsCount();
        this.aCols = A.getColsCount();
        this.bCols = B.getColsCount();
        this.A = A.getArray();
        this.B = B.getArray();
        this.C = C.getArray();
        sumTread++;
        numderTread = sumTread;
    }

    @Override
    public void run() {

        System.out.println("The tread number " + numderTread + " started.");

        while (!stopTread) {
            aRows = MatrixDouble.getRowsForTreadWithInkrement();
            //System.out.println("The tread number " + numderTread + ". Rows = " + aRows + ".");
            if (aRows >= rowTotal) {
                stopTread();
            } else {
                multiply(aRows);
            }
        }
        System.out.println("the stream number " + numderTread + " stopped.");
    }

    private void multiply(int aRows) {
        int step = MatrixDouble.stepRow;
        int range = aRows + step;
        for (int i = aRows; i < range; i++) {
            for (int j = 0; j < bCols; j++) {
                double temp = 0;
                for (int k = 0; k < aCols; k++) {
                    temp += A.get(i).get(k) * B.get(k).get(j);
                }
                C.get(i).set(j, temp);
            }
        }

    }

    public void stopTread() {
        stopTread = true;
    }

}
