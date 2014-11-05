package buzov.task3.matrix;

import buzov.task3.matrix.otput.WriterMatrix;
import buzov.task3.matrix.input.ReaderMatrix;
import buzov.task3.matrix.creat.CreatorMatrix;
import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    public static void main(String[] args) {

        try {
            int test = 0;
            int rowsA = 23;
            int colsA = 23;
            int rowsB = colsA;
            int colsB = rowsA;
            String fileNameOfMatrixA = "A" + rowsA + "x" + colsA + ".txt";
            String fileNameOfMatrixB = "B" + rowsB + "x" + colsB + ".txt";
            
            switch (test) {
                case 0:
                    Matrix A = new MatrixDouble(CreatorMatrix.makeRandomDouble(rowsA, colsA));
                    A.write(fileNameOfMatrixA);
                    A = null;
                    Matrix B = new MatrixDouble(CreatorMatrix.makeRandomDouble(rowsB, colsB));
                    B.write(fileNameOfMatrixB);
                    B = null;
                    
                case 1:
                    
                    String fileNameOfMatrixCDouble1 = "C" + rowsA + "x" + colsB + "forJavaDouble" + ".txt";
                    
                    Matrix A2 = ReaderMatrix.readFromFile(fileNameOfMatrixA, DataType.DOUBLE);
                    Matrix B2 = ReaderMatrix.readFromFile(fileNameOfMatrixB, DataType.DOUBLE);
                    
                    Matrix C2 = B2.multiply(A2, B2);
                    C2.write(fileNameOfMatrixCDouble1);
                    
                    //break;
                case 2:
                    /*String fileNameOfMatrixCDouble2 = "C" + rowsA + "x" + colsB + "forJavaTreadDouble" + ".txt";
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
                    */
                    break;
                case 3:
                    /*//BigDecimal a = new BigDecimal("" + 5);
                    //BigDecimal b= new BigDecimal(5);
                    //System.out.println(a!=b);
                    
                    double a = 13.5465;
                    BigDecimal num = new BigDecimal("" + a).setScale(3, BigDecimal.ROUND_UP);
                    System.out.println(num.doubleValue());
                    num = new BigDecimal(a).setScale(3, BigDecimal.ROUND_UP);
                    System.out.println(num.doubleValue());
                    default:
                    System.out.println("The maximum number of processors available to the virtual machine: "
                    + Runtime.getRuntime().availableProcessors() + ".");
                    System.out.println("Exit");*/
                    
            }
        } catch (MatrixIndexOutOfBoundsException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalSizesException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
