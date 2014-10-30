package matrix;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import matrix.err.MatrixIndexOutOfBoundsException;
import matrix.err.WrongRangeException;

public class ReaderMatrix {

    public static Matrix fromFile(String path) throws MatrixIndexOutOfBoundsException {
        int cols;
        int rows;
        double[][] arr = null;

        try {
            Scanner in = new Scanner(new FileReader(path));
            rows = in.nextInt();
            cols = in.nextInt();
            arr = new double[rows][cols];
            in.useLocale(Locale.US);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // TODO exception wrong data
                    arr[i][j] = in.nextDouble();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }

        return new Matrix(arr);
    }

    public static void fromKeyboard(Matrix m) throws MatrixIndexOutOfBoundsException {
        int cols = m.colsCount();
        int rows = m.rowsCount();

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            System.out.println("Enter the string number " + (i + 1));
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the item number " + (j + 1));
                // TODO exception wrong data
                m.setValue(i, j, in.nextDouble());
            }
        }
    }

}
