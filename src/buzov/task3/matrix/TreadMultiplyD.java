package buzov.task3.matrix;

public class TreadMultiplyD implements Runnable {

    private boolean stopTread = false;
    private int numderTread = 0;
    private static int sumTread = 0;
    private int rowTotal;
    private int aRows;
    private int aCols;
    private int bCols;
    private double[][] A;
    private double[][] B;
    private double[][] C;

    public TreadMultiplyD(double[][] A, double[][] B, double[][] C) {
        rowTotal = A.length;
        this.aCols = A[0].length;
        this.bCols = B[0].length;;
        this.A = A;
        this.B = B;
        this.C = C;
        sumTread++;
        numderTread = sumTread;
    }

    public TreadMultiplyD(MatrixDouble A, MatrixDouble B, MatrixDouble C) {
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
        System.out.println("The stream number " + numderTread + " stopped.");
    }

    private void multiply(int aRows) {
        int step = MatrixDouble.stepRow;
        int range = aRows + step;
        for (int i = aRows; i < range; i++) {
            for (int j = 0; j < bCols; j++) {
                for (int k = 0; k < aCols; k++) {
                    C[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }

    }

    public void stopTread() {
        stopTread = true;
    }

}
