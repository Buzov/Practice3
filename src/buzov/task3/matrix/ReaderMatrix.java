package buzov.task3.matrix;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import buzov.task3.matrix.err.MatrixIndexOutOfBoundsException;

public class ReaderMatrix {

    public static double[][] fromFileInDouble(String path) throws MatrixIndexOutOfBoundsException {
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
                    // TODO exception wrong data
                    arr[i][j] = in.nextDouble();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не найден указанный файл!");
        }

        return arr;
    }
    
    
    public static MatrixD fromFileInMatrix(String path) throws MatrixIndexOutOfBoundsException {
        
        MatrixD C = new MatrixD(fromFileInDouble(path));
        
        return C;
    }
    
    public static ArrayList<ArrayList<Double>> fromFileInArray(String path) throws MatrixIndexOutOfBoundsException {
        int rows;
        int cols;
        
        ArrayList<ArrayList<Double>> arr = null;

        try {
            Scanner in = new Scanner(new FileReader(path));
            rows = in.nextInt();
            cols = in.nextInt();
            arr = new ArrayList<>(/*rows*/);
            for(int i = 0; i < rows; i++) {
                arr.add(new ArrayList<Double>(cols));
            }
                    //double[rows][cols];
            in.useLocale(Locale.US);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // TODO exception wrong data
                    arr.get(i).add(in.nextDouble());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не найден указанный файл!");
        }

        return arr;
    }
    
    public static MatrixArr fromFileInMatrixArray(String path) throws MatrixIndexOutOfBoundsException {
        
        MatrixArr C = new MatrixArr(fromFileInArray(path));
        
        return C;
    }

    public static void fromKeyboard(MatrixD m) throws MatrixIndexOutOfBoundsException {
        int rows = m.getRowsCount();
        int cols = m.getColsCount();
        
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
    
    public static void fromKeyboard(MatrixArr m) throws MatrixIndexOutOfBoundsException {
        int rows = m.getRowsCount();
        int cols = m.getColsCount();
        

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
