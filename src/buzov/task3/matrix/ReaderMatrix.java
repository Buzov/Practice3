package buzov.task3.matrix;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;

public class ReaderMatrix {

    public static double[][] readFromFileInDouble(String path) throws MatrixIndexOutOfBoundsException {
        int rows;
        int cols;

        double[][] arr = null;

        try {
            Scanner in = new Scanner(new FileReader(path));
            rows = in.nextInt();
            cols = in.nextInt();
            arr = new double[rows][cols];
            in.useLocale(Locale.US);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    arr[i][j] = in.nextDouble();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The specified file is not found!");
        }

        return arr;
    }

    public static MatrixDouble readFromFileInMatrixDouble(String path) throws MatrixIndexOutOfBoundsException {

        MatrixDouble C = new MatrixDouble(readFromFileInDouble(path));

        return C;
    }

    public static ArrayList<ArrayList<Double>> readFromFileInArray(String path) throws MatrixIndexOutOfBoundsException {
        int rows;
        int cols;

        ArrayList<ArrayList<Double>> arr = null;

        try {
            Scanner in = new Scanner(new FileReader(path));
            rows = in.nextInt();
            cols = in.nextInt();
            arr = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                arr.add(new ArrayList<Double>(cols));
            }
            in.useLocale(Locale.US);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    arr.get(i).add(in.nextDouble());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The specified file is not found!");
        }

        return arr;
    }

    public static MatrixArray readFromFileInMatrixArray(String path) throws MatrixIndexOutOfBoundsException {

        MatrixArray C = new MatrixArray(readFromFileInArray(path));

        return C;
    }

    public static void readFromKeyboard(MatrixDouble m) throws MatrixIndexOutOfBoundsException {
        int rows = m.getRowsCount();
        int cols = m.getColsCount();

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            System.out.println("Enter the string number " + (i + 1));
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the item number " + (j + 1));
                m.setValue(i, j, in.nextDouble());
            }
        }
    }

    public static void readFromKeyboard(MatrixArray m) throws MatrixIndexOutOfBoundsException {
        
        int rows = m.getRowsCount();
        int cols = m.getColsCount();

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            System.out.println("Enter the string number " + (i + 1));
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the item number " + (j + 1));
                m.setValue(i, j, in.nextDouble());
            }
        }
    }

}
