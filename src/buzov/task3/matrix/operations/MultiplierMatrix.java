package buzov.task3.matrix.operations;

import buzov.task3.matrix.DataType;
import buzov.task3.matrix.Matrix;
import static buzov.task3.matrix.MatrixAbstract.getRowsForThread;
import static buzov.task3.matrix.MatrixAbstract.setZeroRowsForThread;
import buzov.task3.matrix.MatrixArray;
import buzov.task3.matrix.MatrixDouble;
import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.tread.ThreadSelector;
import java.util.ArrayList;

public class MultiplierMatrix {

    public static Matrix multiply(Matrix A, Matrix B, DataType dataType) throws IllegalSizesException {

        Object objectA = A.getArray();
        Object objectB = B.getArray();
        Matrix C = null;
        int rowsA = 0;
        int colsA = 0;
        int rowsB = 0;
        int colsB = 0;

        long startTime = System.currentTimeMillis();

        switch (dataType) {
            case DOUBLE:
                double[][] matrixDoubleA = (double[][]) objectA;
                double[][] matrixDoubleB = (double[][]) objectB;
                rowsA = matrixDoubleA.length;
                colsA = matrixDoubleA[0].length;
                rowsB = matrixDoubleB.length;
                colsB = matrixDoubleB[0].length;
                if (colsA != rowsB) {
                    throw new RuntimeException("Illegal matrix dimensions.");
                }
                double[][] matrixDoubleC = new double[rowsA][colsB];
                for (int i = 0; i < rowsA; i++) {
                    for (int j = 0; j < colsB; j++) {
                        for (int k = 0; k < colsA; k++) {
                            matrixDoubleC[i][j] += (matrixDoubleA[i][k] * matrixDoubleB[k][j]);
                        }
                    }
                }
                System.out.println("Type of data is double[][].");
                C = new MatrixDouble(matrixDoubleC);
                break;
            case ARRAY:
                ArrayList<ArrayList<Double>> matrixArrA = (ArrayList<ArrayList<Double>>) objectA;
                ArrayList<ArrayList<Double>> matrixArrB = (ArrayList<ArrayList<Double>>) objectB;
                rowsA = matrixArrA.size();
                colsA = matrixArrA.get(0).size();
                rowsB = matrixArrB.size();
                colsB = matrixArrB.get(0).size();
                if (colsA != rowsB) {
                    throw new RuntimeException("Illegal matrix dimensions.");
                }
                System.out.println("Type of data is ArrayList<ArrayList<Double>>.");
                ArrayList<ArrayList<Double>> matrixArrC = new ArrayList<>();

                for (int i = 0; i < rowsA; i++) {
                    matrixArrC.add(new ArrayList<Double>());
                    for (int j = 0; j < colsB; j++) {
                        double temp = 0;
                        for (int k = 0; k < colsA; k++) {
                            temp += matrixArrA.get(i).get(k) * matrixArrB.get(k).get(j);
                        }
                        matrixArrC.get(i).add(temp);
                    }
                }
                C = new MatrixArray(matrixArrC);
                break;
        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Multiplication of matrixes lasted " + time + " ms.");

        System.out.println("Multiplication of matrixes A" + rowsA
                + "x" + colsA + " and B" + rowsB
                + "x" + colsB + ".");

        return C;
    }

    public static Matrix multiplyThread(Matrix A, Matrix B, DataType dataType) {

        Object objectA = A.getArray();
        Object objectB = B.getArray();
        Matrix C = null;
        int rowsA = 0;
        int colsA = 0;
        int rowsB = 0;
        int colsB = 0;

        switch (dataType) {
            case DOUBLE:
                double[][] matrixDoubleA = (double[][]) objectA;
                double[][] matrixDoubleB = (double[][]) objectB;
                rowsA = matrixDoubleA.length;
                colsA = matrixDoubleA[0].length;
                rowsB = matrixDoubleB.length;
                colsB = matrixDoubleB[0].length;

                System.out.println("Type of data is double[][].");
                C = new MatrixDouble(rowsA, colsB);
            case ARRAY:
                ArrayList<ArrayList<Double>> matrixArrA = (ArrayList<ArrayList<Double>>) objectA;
                ArrayList<ArrayList<Double>> matrixArrB = (ArrayList<ArrayList<Double>>) objectB;
                rowsA = matrixArrA.size();
                colsA = matrixArrA.get(0).size();
                rowsB = matrixArrB.size();
                colsB = matrixArrB.get(0).size();

                System.out.println("Type of data is ArrayList<ArrayList<Double>>.");
                C = new MatrixArray(rowsA, colsB);

        }

        if (colsA != rowsB) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }

        System.out.println("Type of data is ArrayList<ArrayList<Double>>.");
        System.out.println("Multitread multiplication of matrixes A " + rowsA
                + "x" + colsA + " and B" + rowsB
                + "x" + colsB + ".");

        int quantityOfStreams;
        if (rowsA <= 100) {
            quantityOfStreams = 1;
        } else {
            //The maximum number of processors available to the virtual machine
            quantityOfStreams = Runtime.getRuntime().availableProcessors();
        }

        Thread[] thrd = new Thread[quantityOfStreams];
        ThreadSelector threadSelector = new ThreadSelector(A, B, C);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < quantityOfStreams; i++) {
            thrd[i] = new Thread(threadSelector.getThread(dataType));
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
        setZeroRowsForThread();
        System.out.println(getRowsForThread());

        return C;
    }
}
