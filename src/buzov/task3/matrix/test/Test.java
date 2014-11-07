package buzov.task3.matrix.test;

import buzov.task3.matrix.DataType;
import buzov.task3.matrix.Matrix;
import buzov.task3.matrix.MatrixSelector;
import buzov.task3.matrix.input.ReaderMatrix;

public class Test {

    public static void main(String[] args) {

        try {
            int test = 0;
            int rowsA = 10;
            int colsA = 10;
            int rowsB = colsA;
            int colsB = rowsA;
            String fileNameOfMatrixA = "A" + rowsA + "x" + colsA + ".txt";
            String fileNameOfMatrixB = "B" + rowsB + "x" + colsB + ".txt";
            Matrix A = null;
            Matrix B = null;
            Matrix C = null;
            switch (test) {
                case 0:
                    A = MatrixSelector.getMatrix(rowsA, colsA, DataType.DOUBLE);

                    A.initialize();
                    
                    A.write(fileNameOfMatrixA);
                    //A = null;
                    B = MatrixSelector.getMatrix(rowsB, colsB, DataType.DOUBLE);
                    B.initialize();
                    B.write(fileNameOfMatrixB);
                    //B = null;
                    System.out.println(A.getDataType().equals(B.getDataType()));

                //break;
                case 1:

                    String fileNameOfMatrixCDouble1 = "C" + rowsA + "x" + colsB + "forJavaDouble" + ".txt";
                    A = MatrixSelector.getMatrix(rowsA, colsA, DataType.INTEGER);
                    B = MatrixSelector.getMatrix(rowsB, colsB, DataType.INTEGER);
                    A.read(fileNameOfMatrixA);
                    B.read(fileNameOfMatrixB);

                    A.print();
                    B.print();
                    C = A.multiply(B);
                    //C.print();
                    A = null;
                    B = null;
                    C.write(fileNameOfMatrixCDouble1);
                    C = null;
                break;
                case 2:
                    String fileNameOfMatrixCDouble2 = "C" + rowsA + "x" + colsB
                            + "forJavaThreadDouble" + ".txt";
                    A = ReaderMatrix.readFromFile(fileNameOfMatrixA, DataType.DOUBLE);
                    B = ReaderMatrix.readFromFile(fileNameOfMatrixB, DataType.DOUBLE);

                    C = A.multiplyThread(B);
                    C.write(fileNameOfMatrixCDouble2);
                    //A.print();
                    //B.print();
                    //C.print();
                    break;

            }
        } catch (Exception x) {
            System.out.println(x);
        }
    }
}
