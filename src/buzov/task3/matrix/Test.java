package buzov.task3.matrix;

import java.util.ArrayList;
import buzov.task3.matrix.err.IllegalSizesException;
import buzov.task3.matrix.err.MatrixIndexOutOfBoundsException;

public class Test {

    public static void main(String[] args) throws MatrixIndexOutOfBoundsException, IllegalSizesException {

        int test = 1;

        switch (test) {
            case 0:
                int rows = 3;
                int cols = 3;
                MatrixD A2 = new MatrixD(CreatorMatrix.makeRandom(rows, cols));
                MatrixD B2 = new MatrixD(CreatorMatrix.makeRandom(rows, cols));
                WriterMatrix.toFile(A2, "Ax.txt");
                WriterMatrix.toFile(B2, "Bx.txt");
                test = 3;
                break;
                
            case 1:
                MatrixD A = ReaderMatrix.fromFileInMatrix("Az.txt");
                MatrixD B = ReaderMatrix.fromFileInMatrix("Bz.txt");
                /*for (int i = 0; i < 10; i++) {
                    MatrixD.multiply(A, B);
                }*/

                System.out.println("-----------------------------------");
                //WriterMatrix.toFile(C, "C_1.txt");
                /*for (int i = 0; i < 10; i++) {
                    MatrixD.multiplyTread(A.getArray(), B.getArray());
                }*/
                MatrixD CC = new MatrixD(MatrixD.multiplyTread(A.getArray(), B.getArray()));
                CC.print();
                //WriterMatrix.toFile(D, "C_2.txt");
                break;
            case 2:
                MatrixD Ad = ReaderMatrix.fromFileInMatrix("Ax.txt");
                MatrixD Bd = ReaderMatrix.fromFileInMatrix("Bx.txt");
                /*              
                 
                 
                 for (int i = 0; i < 10; i++) {
                 MatrixD.multiply(Ad, Bd);
                 }*/
                MatrixArr Aarr = ReaderMatrix.fromFileInMatrixArray("Ax.txt");
                MatrixArr Barr = ReaderMatrix.fromFileInMatrixArray("Bx.txt");

                for (int i = 0; i < 10; i++) {
                    MatrixArr.multiply(Aarr, Barr);
                }
                break;
            case 3:
                MatrixD Ad2 = ReaderMatrix.fromFileInMatrix("Az.txt");
                MatrixD Bd2 = ReaderMatrix.fromFileInMatrix("Bz.txt");
                MatrixArr Aarr2 = ReaderMatrix.fromFileInMatrixArray("Az.txt");
                MatrixArr Barr2 = ReaderMatrix.fromFileInMatrixArray("Bz.txt");
                MatrixD C3 = MatrixD.multiply(Ad2, Bd2);
                MatrixArr Carr3 = MatrixArr.multiply(Aarr2, Barr2);
                WriterMatrix.toFile(C3, "C_3.txt");
                WriterMatrix.toFile(Carr3, "C_4.txt");
                Ad2.print();
                System.out.println("--------------------------------------------");
                MatrixD.print(Bd2.mas);
                System.out.println(Bd2.getValue(0, 0));   
                System.out.println("--------------------------------------------");
                Aarr2.print();
                System.out.println("--------------------------------------------");
                Barr2.print();
                System.out.println("--------------------------------------------");
                C3.print();
                System.out.println("--------------------------------------------");
                Carr3.print();
                
                break;
            case 4:
                MatrixArr Aarr4 = ReaderMatrix.fromFileInMatrixArray("Az.txt");
                MatrixArr Barr4 = ReaderMatrix.fromFileInMatrixArray("Bz.txt");
                /*for (int i = 0; i < 10; i++) {
                    MatrixD.multiply(A, B);
                }*/

                System.out.println("-----------------------------------");
                //WriterMatrix.toFile(C, "C_1.txt");
                /*for (int i = 0; i < 10; i++) {
                    MatrixD.multiplyTread(A.getArray(), B.getArray());
                }*/
                MatrixArr Carr4 = new MatrixArr(MatrixArr.multiplyTread(Aarr4.getArray(), 
                                                                        Barr4.getArray()));
                Carr4.print();
                //WriterMatrix.toFile(D, "C_2.txt");
                break;
               
            case 5:
                break;
            default:
        }

    }

}
