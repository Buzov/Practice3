package buzov.task3.matrix.tread;

import buzov.task3.matrix.Matrix;
import buzov.task3.matrix.MatrixDouble;
 
public class MultiplyThreadDouble extends ThreadAbstract {

    private double[][] A;
    private double[][] B;
    private double[][] C;

    protected MultiplyThreadDouble(Matrix A, Matrix B, Matrix C) {
        rowTotal = A.getRowsCount();
        this.colsA = A.getColsCount();
        this.colsB = B.getColsCount();
        this.A = (double[][]) A.getArray();
        this.B = (double[][]) B.getArray();
        this.C = (double[][]) C.getArray();
        sumTread++;
        numderTread = sumTread;
    }
    
    @Override
    protected void multiply(int aRows) {
        int step = MatrixDouble.stepRow;
        int range = aRows + step;
        for (int i = aRows; i < range; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }

    }


}
