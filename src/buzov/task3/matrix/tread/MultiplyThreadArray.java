package buzov.task3.matrix.tread;

import buzov.task3.matrix.Matrix;
import buzov.task3.matrix.MatrixDouble;
import java.util.ArrayList;

public class MultiplyThreadArray extends ThreadAbstract {

    private ArrayList<ArrayList<Double>> A;
    private ArrayList<ArrayList<Double>> B;
    private ArrayList<ArrayList<Double>> C;

    protected MultiplyThreadArray(Matrix A, Matrix B, Matrix C) {
        rowTotal = A.getRowsCount();
        this.colsA = A.getColsCount();
        this.colsB = B.getColsCount();
        this.A = (ArrayList<ArrayList<Double>>) A.getArray();
        this.B = (ArrayList<ArrayList<Double>>) B.getArray();
        this.C = (ArrayList<ArrayList<Double>>) C.getArray();
        sumTread++;
        numderTread = sumTread;
    }

    @Override
    protected void multiply(int aRows) {
        int step = MatrixDouble.stepRow;
        int range = aRows + step;
        for (int i = aRows; i < range; i++) {
            C.add(new ArrayList<Double>());
            for (int j = 0; j < colsB; j++) {
                double temp = 0;
                for (int k = 0; k < colsA; k++) {
                    temp += A.get(i).get(k) * B.get(k).get(j);
                }
                C.get(i).add(temp);
            }
        }

    }

}
