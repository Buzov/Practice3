package buzov.task3.matrix.input;

import buzov.task3.matrix.*;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ReaderMatrix {

    public static Matrix readFromFile(String path, DataType dataType) throws MatrixIndexOutOfBoundsException {
        Matrix matrix = null;
        int rows;
        int cols;
        try {

            //new MatrixDouble(readFromFileInDouble(path));
            Scanner in = new Scanner(new FileReader(path));
            rows = in.nextInt();
            cols = in.nextInt();

            switch (dataType) {
                case DOUBLE:
                    double[][] matrixDouble = new double[rows][cols];
                    in.useLocale(Locale.US);
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            matrixDouble[i][j] = in.nextDouble();
                        }
                    }
                    matrix = new MatrixDouble(matrixDouble);
                    break;
                case ARRAY:
                    ArrayList<ArrayList<Double>> matrixArray = new ArrayList<>();
                    for (int i = 0; i < rows; i++) {
                        matrixArray.add(new ArrayList<Double>(cols));
                    }
                    in.useLocale(Locale.US);
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            matrixArray.get(i).add(in.nextDouble());
                        }
                    }
                    matrix = new MatrixArray(matrixArray);
                    break;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("The specified file is not found!");
        }
        return matrix;
    }
}
