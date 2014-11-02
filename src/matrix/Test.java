package matrix;

import java.util.ArrayList;
import matrix.err.IllegalSizesException;
import matrix.err.MatrixIndexOutOfBoundsException;

public class Test {

    public static void main(String[] args) throws MatrixIndexOutOfBoundsException, IllegalSizesException {

        int test = 2;

        switch (test) {
            case 0:
                int rows = 300;
                int cols = 300;
                MatrixD A2 = new MatrixD(CreatorMatrix.makeRandom(rows, cols));
                MatrixD B2 = new MatrixD(CreatorMatrix.makeRandom(rows, cols));
                WriterMatrix.toFile(A2, "Ax.txt");
                WriterMatrix.toFile(B2, "Bx.txt");
            case 1:
                MatrixD A = ReaderMatrix.fromFileInMatrix("Ax.txt");
                MatrixD B = ReaderMatrix.fromFileInMatrix("Bx.txt");
                for (int i = 0; i < 10; i++) {
                    MatrixD.multiply(A, B);
                }

                System.out.println("-----------------------------------");
                //WriterMatrix.toFile(C, "C_1.txt");
                for (int i = 0; i < 10; i++) {
                    MatrixD.multiplyTread(A.getArray(), B.getArray());
                }

            //WriterMatrix.toFile(D, "C_2.txt");
            case 2:
                MatrixD Ad = ReaderMatrix.fromFileInMatrix("Ax.txt");
                 MatrixD Bd = ReaderMatrix.fromFileInMatrix("Bx.txt");
                /*int aRowsd = Ad.getRowsCount();
                int aColsd = Ad.getColsCount();
                int bRowsd= Bd.getRowsCount();
                int bColsd = Bd.getColsCount();
                
                System.out.println("aRowsd = " + aRowsd);
                System.out.println("aColsd = " + aColsd);
                System.out.println("bRowsd = " + bRowsd);
                System.out.println("bColsd = " + bColsd);
                 
                 
                 
                 for (int i = 0; i < 10; i++) {
                 MatrixD.multiply(Ad, Bd);
                 }*/
                MatrixArr Aarr = ReaderMatrix.fromFileInMatrixArray("Ax.txt");
                MatrixArr Barr = ReaderMatrix.fromFileInMatrixArray("Bx.txt");
                
             
                
                /*int aRows = Aarr.getRowsCount();
                int aCols = Aarr.getColsCount();
                int bRows = Barr.getRowsCount();
                int bCols = Barr.getColsCount();
                
                System.out.println("aRows = " + aRows);
                System.out.println("aCols = " + aCols);
                System.out.println("bRows = " + bRows);
                System.out.println("bCols = " + bCols);*/

            for (int i = 0; i < 10; i++) {
             MatrixArr.multiply(Aarr, Barr);
             }
            case 3:
            case 4:
            case 5:
            default:
        }

    }

}
