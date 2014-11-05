package buzov.task3.matrix.tread;

import buzov.task3.matrix.MatrixDouble;

public abstract class ThreadAbstract implements Runnable {

    protected boolean stopTread = false;
    protected int numderTread = 0;
    protected static int sumTread = 0;
    protected int rowTotal;
    protected int rowsA;
    protected int colsA;
    protected int colsB;

    @Override
    public void run() {

        System.out.println("The tread number " + numderTread + " started.");

        while (!stopTread) {
            rowsA = MatrixDouble.getRowsForThreadWithInkrement();
            //System.out.println("The tread number " + numderTread + ". Rows = " + aRows + ".");
            if (rowsA >= rowTotal) {
                stopTread();
            } else {
                multiply(rowsA);
            }
        }
        System.out.println("The stream number " + numderTread + " stopped.");
    }

    protected abstract void multiply(int aRows);

    public void stopTread() {
        stopTread = true;
    }
}
