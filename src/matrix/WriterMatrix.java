package matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import matrix.err.MatrixIndexOutOfBoundsException;

public class WriterMatrix {

    public static void toFile(MatrixD A, String path) throws MatrixIndexOutOfBoundsException {
        int rows = A.getRowsCount();
        int cols = A.getColsCount();
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(rows + " " + cols + "\r\n");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    bw.write(A.getValue(i, j) + " ");
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
    
    public static void toFile(double [][] A, String path) throws MatrixIndexOutOfBoundsException {
        int rows = A.length;
        int cols = A[0].length;
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(rows + " " + cols + "\r\n");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    bw.write(A[i][j] + " ");
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
    
    public static void toFile(ArrayList<ArrayList<Double>> A, String path) throws MatrixIndexOutOfBoundsException {
        int rows = A.size();;
        int cols = A.get(0).size();
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(rows + " " + cols + "\r\n");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    bw.write(A.get(i).get(j) + " ");
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
