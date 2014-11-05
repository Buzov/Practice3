
import buzov.task3.matrix.Matrix;
import buzov.task3.matrix.MatrixAbstract;
import buzov.task3.matrix.MatrixArray;
import buzov.task3.matrix.MatrixDouble;
import buzov.task3.matrix.creat.CreatorMatrix;
import org.apache.commons.math3.linear.*;

public class Test2 {

    public static void main(String[] args) {
        
        int rowsA = 1000;
        int colsA = 1000;
        int rowsB = colsA;
        int colsB = rowsA;
        
        
        double [][] a = CreatorMatrix.makeRandomDouble(rowsA, colsA);
        RealMatrix n = new Array2DRowRealMatrix(a);
        double [][] b = CreatorMatrix.makeRandomDouble(rowsB, colsB);
        RealMatrix m = new Array2DRowRealMatrix(b);
        
        long startTime = System.currentTimeMillis();
        RealMatrix p = n.multiply(m);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Multiplication of matrixes lasted " + time + " ms.");
        
        System.out.println(p);
        RealMatrix d = p.transpose();
        System.out.println(d);
        Matrix v = new MatrixArray(rowsB, colsB);
        Matrix vc = new MatrixArray(rowsB, colsB);
    }
}
