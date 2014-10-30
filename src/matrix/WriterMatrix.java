package matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import matrix.err.MatrixIndexOutOfBoundsException;

public class WriterMatrix {

    public static void toFile(Matrix m, String path) throws MatrixIndexOutOfBoundsException {
        int rows = m.rowsCount();
        int cols = m.colsCount();
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(rows + " " + cols + "\r\n");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    bw.write(m.getValue(i, j) + " ");
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
