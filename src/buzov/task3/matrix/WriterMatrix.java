package buzov.task3.matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;

public class WriterMatrix {

    public static void write(MatrixDouble mA, String path) throws MatrixIndexOutOfBoundsException {

        double[][] A = mA.getArray();
        WriterMatrix.write(A, path);

    }

    public static void write(double[][] A, String path) throws MatrixIndexOutOfBoundsException {
        int rows = A.length;
        int cols = A[0].length;
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(rows + " " + cols + "\r\n");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    bw.write(CreatorMatrix.roundNumber(A[i][j], 3) + " ");
                }
                bw.write("\r\n");
            }
        } catch (IOException e) {
            System.out.println("Не удается произвести запись в указанный файл");
        }

        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Ошибка ввода / вывода");
        }
    }

    public static void write(MatrixArray mA, String path) throws MatrixIndexOutOfBoundsException {
        
        ArrayList<ArrayList<Double>> A = mA.getArray();
        
        write(A, path);
    }
    
    public static void write(ArrayList<ArrayList<Double>> A, String path) throws MatrixIndexOutOfBoundsException {
        int rows = A.size();;
        int cols = A.get(0).size();
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(rows + " " + cols + "\r\n");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    bw.write(CreatorMatrix.roundNumber(A.get(i).get(j), 3) + " ");
                }
                
                bw.write("\r\n");
            }
        } catch (IOException e) {
            System.out.println("Не удается произвести запись в указанный файл");
        }

        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Ошибка ввода / вывода");
        }
    }

    
}
