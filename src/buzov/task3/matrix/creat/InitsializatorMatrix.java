package buzov.task3.matrix.creat;

import buzov.task3.matrix.DataType;
import buzov.task3.matrix.Matrix;
import buzov.task3.matrix.tread.MultiplyThreadArray;
import buzov.task3.matrix.tread.MultiplyThreadDouble;
import java.math.BigDecimal;
import java.util.ArrayList;

public class InitsializatorMatrix {

    private static final int accuracy = 3;

    public static BigDecimal roundNumber(double value, int digits) {
        //we approximate the transferred number "value" with accuracy "digits"          
        BigDecimal num = new BigDecimal(value).setScale(digits, BigDecimal.ROUND_UP);
        return num;
    }

    public static void makeRandom(Matrix matrix, DataType dataType) {
        Object objectA = matrix.getArray();
        int rows = 0;
        int cols = 0;

        switch (dataType) {
            case DOUBLE:
                
                double[][] matrixDoubleA = (double[][]) objectA;
                rows = matrixDoubleA.length;
                cols = matrixDoubleA[0].length;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        // fill with random values
                        matrixDoubleA[i][j] = roundNumber(Math.random() * 10, accuracy).doubleValue();
                    }
                }
                break;
            case ARRAY:
                ArrayList<ArrayList<Double>> matrixArrA = (ArrayList<ArrayList<Double>>) objectA;
                rows = matrixArrA.size();
                cols = matrixArrA.get(0).size();
                double temp = 0;
                for (int i = 0; i < rows; i++) {
                    matrixArrA.add(new ArrayList<Double>());
                    for (int j = 0; j < cols; j++) {
                        // fill with random values
                        temp = roundNumber(Math.random() * 10, accuracy).doubleValue();
                        matrixArrA.get(i).add(temp);
                    }
                }
                break;
        }

    }
}
