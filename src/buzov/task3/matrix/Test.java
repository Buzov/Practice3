package buzov.task3.matrix;

import buzov.task3.matrix.otput.WriterMatrix;
import buzov.task3.matrix.input.ReaderMatrix;
import buzov.task3.matrix.creat.InitsializatorMatrix;
import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    public static void main(String[] args) {

        try {
            int test = 2;
            int rowsA = 10000;
            int colsA = 10000;
            int rowsB = colsA;
            int colsB = rowsA;
            String fileNameOfMatrixA = "A" + rowsA + "x" + colsA + ".txt";
            String fileNameOfMatrixB = "B" + rowsB + "x" + colsB + ".txt";
            Matrix A = null;
            Matrix B = null;
            Matrix C = null;
            switch (test) {
                case 0:
                    A = new MatrixDouble(rowsA, colsA);
                    A.initialize();
                    A.write(fileNameOfMatrixA);
                    A = null;
                    B = new MatrixDouble(rowsB, colsB);
                    B.initialize();
                    B.write(fileNameOfMatrixB);
                    B = null;

                case 1:

                    String fileNameOfMatrixCDouble1 = "C" + rowsA + "x" + colsB + "forJavaDouble" + ".txt";
                    A = MatrixSelector.getMatrix(rowsA, colsA, DataType.DOUBLE);
                    B = MatrixSelector.getMatrix(rowsB, colsB, DataType.DOUBLE);
                    A.read(fileNameOfMatrixA);
                    B.read(fileNameOfMatrixB);

                    C = A.multiply(B);
                    A = null;
                    B = null;
                    C.write(fileNameOfMatrixCDouble1);
                    C = null;

                //break;
                case 2:
                    String fileNameOfMatrixCDouble2 = "C" + rowsA + "x" + colsB
                            + "forJavaThreadDouble" + ".txt";
                    A = ReaderMatrix.readFromFile(fileNameOfMatrixA, DataType.DOUBLE);
                    B = ReaderMatrix.readFromFile(fileNameOfMatrixB, DataType.DOUBLE);

                    C = A.multiplyThread(B);
                    C.write(fileNameOfMatrixCDouble2);

                    break;

            }
        } catch (Exception x) {
            System.out.println(x);
        }
    }
}
