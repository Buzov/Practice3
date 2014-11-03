package buzov.task3.matrix;

import buzov.task3.matrix.err.IllegalSizesException;
import buzov.task3.matrix.err.MatrixIndexOutOfBoundsException;

public class Test {

    public static void main(String[] args) throws MatrixIndexOutOfBoundsException, IllegalSizesException {

        int test = 0;
        int rowsA = 50; //
        int colsA = 20;
        int rowsB = colsA;
        int colsB = rowsA;
        String fileNameOfMatrixA = "A" + rowsA + "x" + colsA + ".txt";
        String fileNameOfMatrixB = "B" + rowsB + "x" + colsB + ".txt";

        switch (test) {
            case 0:
                MatrixD Ad0 = new MatrixD(CreatorMatrix.makeRandom(rowsA, colsA));
                MatrixD Bd0 = new MatrixD(CreatorMatrix.makeRandom(rowsB, colsB));
                WriterMatrix.toFile(Ad0, fileNameOfMatrixA);
                WriterMatrix.toFile(Bd0, fileNameOfMatrixB);
            case 1:
                String fileNameOfMatrixCDouble1 = "C" + rowsA + "x" + colsB + "forJavaDouble" + ".txt";

                MatrixD Ad1 = ReaderMatrix.fromFileInMatrix(fileNameOfMatrixA);
                MatrixD Bd1 = ReaderMatrix.fromFileInMatrix(fileNameOfMatrixB);
                System.out.println("aRows = " + Ad1.getRowsCount());
                System.out.println("bCols = " + Bd1.getColsCount());
                MatrixD C1 = MatrixD.multiply(Ad1, Bd1);
                WriterMatrix.toFile(C1, fileNameOfMatrixCDouble1);

                String fileNameOfMatrixCArr1 = "C" + rowsA + "x" + colsB + "forJavaArr" + ".txt";
                MatrixArr Aarr1 = ReaderMatrix.fromFileInMatrixArray(fileNameOfMatrixA);
                MatrixArr Barr1 = ReaderMatrix.fromFileInMatrixArray(fileNameOfMatrixB);
                MatrixArr Carr1 = MatrixArr.multiply(Aarr1, Barr1);
                WriterMatrix.toFile(Carr1, fileNameOfMatrixCArr1);
            case 2:
                String fileNameOfMatrixCDouble2 = "C" + rowsA + "x" + colsB + "forJavaTreadDouble" + ".txt";
                MatrixD Ad2 = ReaderMatrix.fromFileInMatrix(fileNameOfMatrixA);
                MatrixD Bd2 = ReaderMatrix.fromFileInMatrix(fileNameOfMatrixB);
                MatrixD CofTreadDouble = new MatrixD(MatrixD.multiplyTread(Ad2.getArray(), Bd2.getArray()));
                WriterMatrix.toFile(CofTreadDouble, fileNameOfMatrixCDouble2);

                /*for (int i = 0; i < 10; i++) {
                 MatrixD.multiplyTread(A.getArray(), B.getArray());
                 }*/
                String fileNameOfMatrixCArr2 = "C" + rowsA + "x" + colsB + "forJavaTreadArr" + ".txt";
                MatrixArr Aarr2 = ReaderMatrix.fromFileInMatrixArray(fileNameOfMatrixA);
                MatrixArr Barr2 = ReaderMatrix.fromFileInMatrixArray(fileNameOfMatrixB);
                MatrixArr CofTreadArr = new MatrixArr(MatrixArr.multiplyTread(Aarr2.getArray(), Barr2.getArray()));
                WriterMatrix.toFile(CofTreadArr, fileNameOfMatrixCArr2);

                /*for (int i = 0; i < 10; i++) {
                 MatrixArr.multiply(Aarr, Barr);
                 }*/
                break;
            case 3:
            default:
                System.out.println("Exit");
        }

    }

}
