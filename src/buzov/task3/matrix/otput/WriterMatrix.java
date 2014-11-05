package buzov.task3.matrix.otput;

import buzov.task3.matrix.DataType;
import buzov.task3.matrix.Matrix;
import buzov.task3.matrix.creat.InitsializatorMatrix;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriterMatrix {

    public static void write(Matrix matrix, String path, DataType dataType) throws MatrixIndexOutOfBoundsException {
        Object object = matrix.getArray();
        BufferedWriter bw = null;
        int rows = 0;
        int cols = 0;

        switch (dataType) {
            case DOUBLE:
                try {
                    double[][] matrixDoubleA = (double[][]) object;
                    rows = matrixDoubleA.length;
                    cols = matrixDoubleA[0].length;
                    bw = new BufferedWriter(new FileWriter(path));
                    bw.write(rows + " " + cols + "\r\n");
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            bw.write(InitsializatorMatrix.roundNumber(matrixDoubleA[i][j], 3) + " ");
                        }
                        bw.write("\r\n");
                    }
                } catch (IOException e) {
                    System.out.println("Не удается произвести запись в указанный файл");
                }
                break;
            case ARRAY:
                try {
                    ArrayList<ArrayList<Double>> matrixArrA = (ArrayList<ArrayList<Double>>) object;
                    rows = matrixArrA.size();
                    cols = matrixArrA.get(0).size();
                    bw = new BufferedWriter(new FileWriter(path));
                    bw.write(rows + " " + cols + "\r\n");
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            bw.write(InitsializatorMatrix.roundNumber(matrixArrA.get(i).get(j), 3) + " ");
                        }

                        bw.write("\r\n");
                    }
                } catch (IOException e) {
                    System.out.println("Не удается произвести запись в указанный файл");
                }
                break;
        }

        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Ошибка ввода / вывода");
        }

    }

}
