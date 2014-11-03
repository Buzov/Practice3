package buzov.task3.matrix;

import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;

public class Test {

    public static void main(String[] args) {

        try {
            int test = 0;
            int rowsA = 100;
            int colsA = 100;
            int rowsB = colsA;
            int colsB = rowsA;
            String fileNameOfMatrixA = "A" + rowsA + "x" + colsA + ".txt";
            String fileNameOfMatrixB = "B" + rowsB + "x" + colsB + ".txt";

            switch (test) {
                case 0:
                    MatrixDouble Ad0 = new MatrixDouble(CreatorMatrix.makeRandom(rowsA, colsA));
                    MatrixDouble Bd0 = new MatrixDouble(CreatorMatrix.makeRandom(rowsB, colsB));
                    WriterMatrix.write(Ad0, fileNameOfMatrixA);
                    WriterMatrix.write(Bd0, fileNameOfMatrixB);
                case 1:
                    String fileNameOfMatrixCDouble1 = "C" + rowsA + "x" + colsB + "forJavaDouble" + ".txt";

                    MatrixDouble Ad1 = ReaderMatrix.readFromFileInMatrixDouble(fileNameOfMatrixA);
                    MatrixDouble Bd1 = ReaderMatrix.readFromFileInMatrixDouble(fileNameOfMatrixB);
                    System.out.println("aRows = " + Ad1.getRowsCount());
                    System.out.println("bCols = " + Bd1.getColsCount());
                    MatrixDouble C1 = MatrixDouble.multiply(Ad1, Bd1);
                    WriterMatrix.write(C1, fileNameOfMatrixCDouble1);

                    String fileNameOfMatrixCArr1 = "C" + rowsA + "x" + colsB + "forJavaArr" + ".txt";
                    MatrixArray Aarr1 = ReaderMatrix.readFromFileInMatrixArray(fileNameOfMatrixA);
                    MatrixArray Barr1 = ReaderMatrix.readFromFileInMatrixArray(fileNameOfMatrixB);
                    MatrixArray Carr1 = MatrixArray.multiply(Aarr1, Barr1);
                    WriterMatrix.write(Carr1, fileNameOfMatrixCArr1);
                //break;
                case 2:
                    String fileNameOfMatrixCDouble2 = "C" + rowsA + "x" + colsB + "forJavaTreadDouble" + ".txt";
                    MatrixDouble Ad2 = ReaderMatrix.readFromFileInMatrixDouble(fileNameOfMatrixA);
                    MatrixDouble Bd2 = ReaderMatrix.readFromFileInMatrixDouble(fileNameOfMatrixB);
                    MatrixDouble CofTreadDouble = new MatrixDouble(MatrixDouble.multiplyTread(Ad2.getArray(),
                                                                                              Bd2.getArray()));
                    WriterMatrix.write(CofTreadDouble, fileNameOfMatrixCDouble2);

                    String fileNameOfMatrixCArr2 = "C" + rowsA + "x" + colsB + "forJavaTreadArr" + ".txt";
                    MatrixArray Aarr2 = ReaderMatrix.readFromFileInMatrixArray(fileNameOfMatrixA);
                    MatrixArray Barr2 = ReaderMatrix.readFromFileInMatrixArray(fileNameOfMatrixB);
                    MatrixArray CofTreadArr = new MatrixArray(MatrixArray.multiplyTread(Aarr2.getArray(),
                                                                                        Barr2.getArray()));
                    WriterMatrix.write(CofTreadArr, fileNameOfMatrixCArr2);

                    break;
                default:
                    System.out.println("The maximum number of processors available to the virtual machine: "
                            + Runtime.getRuntime().availableProcessors() + ".");
                    System.out.println("Exit");
            }
        } catch (MatrixIndexOutOfBoundsException | IllegalSizesException e) {
            System.out.println("Error:" + e.getMessage());
        }

    }

}
