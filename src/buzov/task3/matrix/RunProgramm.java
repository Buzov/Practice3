package buzov.task3.matrix;

import buzov.task3.matrix.exception.IllegalSizesException;
import buzov.task3.matrix.exception.IncorrectFormatOfData;
import buzov.task3.matrix.exception.MatrixIndexOutOfBoundsException;
import buzov.task3.matrix.input.ReaderMatrix;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class launches the matrix multiplier.
 * 
 * The program can use some threads for multiplication of matrixes.
 * The program can read matrixes from files.
 * <p>The first line of the file should contain two numbers:</p>
 * <ul>
 * <li>the first number is quantity of rows of the matrix;</li>
 * <li>the second number is quantity of rows of the matrix.</li>
 * </ul>
 * 
 * 
 * @author Artur Buzov
 */
public class RunProgramm {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        Matrix matrixA = null;
        Matrix matrixB = null;
        Matrix matrixC = null;
        String exit = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! It is matrix multiplier=)");

        System.out.println("To multiply in one thread input \"1\"");
        System.out.println("To multiply in one several input \"2\"");

        String numberOfThread = "";

        while (true) {
            try {
                numberOfThread = reader.readLine();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            if (numberOfThread.equals("1") || numberOfThread.equals("2")) {
                break;
            }
            System.out.println("Incorrect value. Please try once again");
        }

        boolean inputNumber = false;
        boolean worker = true;

        while (worker) {
            System.out.println("To look at demonstration of work input \"0\".");
            System.out.println("To multiply your matrixes input \"1\".");
            String verificationVariable = "";
            try {
                verificationVariable = reader.readLine();
            } catch (IOException ex) {
                System.out.println(ex);
            }

            switch (verificationVariable) {
                case "0":
                    while (true) {
                        System.out.println("Input quantity of rows of a matrix.");
                        String matrixRowAndCol;
                        int rowsA = 0;
                        while (true) {
                            try {
                                matrixRowAndCol = reader.readLine();
                                rowsA = Integer.parseInt(matrixRowAndCol);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Incorrect value.");
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }

                        }

                        System.out.println("Input quantity of columns of a matrix.");
                        int colsA = 0;
                        while (true) {
                            try {
                                matrixRowAndCol = reader.readLine();
                                colsA = Integer.parseInt(matrixRowAndCol);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Incorrect value.");
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }

                        }

                        int rowsB = colsA;
                        int colsB = rowsA;
                        String fileNameOfMatrixA = "A" + rowsA + "x" + colsA + ".txt";
                        String fileNameOfMatrixB = "B" + rowsB + "x" + colsB + ".txt";

                        matrixA = MatrixSelector.getMatrix(rowsA, colsA, DataType.DOUBLE);
                        try {
                            matrixA.initialize();
                        } catch (MatrixIndexOutOfBoundsException ex) {
                            System.out.println("Error:" + ex);
                        }
                        matrixA.write(fileNameOfMatrixA);

                        matrixB = MatrixSelector.getMatrix(rowsB, colsB, DataType.DOUBLE);
                        try {
                            matrixB.initialize();
                        } catch (MatrixIndexOutOfBoundsException ex) {
                            System.out.println("Error:" + ex);
                        }
                        matrixB.write(fileNameOfMatrixB);
                        String pathMatrixC = "C" + rowsA + "x" + colsB
                                + "forJavaDouble" + ".txt";
                        try {
                            switch (numberOfThread) {
                                case "1":

                                    matrixC = matrixA.multiply(matrixB);

                                    break;
                                case "2":
                                    matrixC = matrixA.multiplyThread(matrixB);
                                    break;
                            }
                        } catch (IllegalSizesException |
                                 IncorrectFormatOfData |
                                 MatrixIndexOutOfBoundsException ex) {
                            System.out.println("Error:" + ex);
                        }

                        matrixC.write(pathMatrixC);

                        System.out.println();
                        System.out.println("To continue press \"Enter\"");
                        System.out.println("To return to the menu input \"1\"");
                        System.out.println("To stop the running program input \"0\".");

                        try {
                            exit = reader.readLine();
                        } catch (IOException ex) {
                            System.out.println("Error:" + ex);
                        }

                        if (exit.equals("1")) {
                            inputNumber = true;
                            break;
                        } else if (exit.equals("0")) {
                            inputNumber = true;
                            worker = false;
                            break;
                        } else {

                        }
                    }

                case "1":
                    System.out.println("Specify the path to the matrix A.");
                    System.out.println("Example: \"c:\\A10X10.txt\".");
                    String pathMatrixA = "";

                    while (true) {
                        try {
                            pathMatrixA = reader.readLine();
                        } catch (IOException ex) {
                            System.out.println("Error:" + ex);
                        }
                        if (new File(pathMatrixA).exists()) {
                            break;
                        }
                        System.out.println("The file is not found.");
                        System.out.println("Specify the path repeatedly.");
                    }

                    System.out.println("Specify the way to the matrix B.");
                    String pathMatrixB = "";

                    while (true) {
                        try {
                            pathMatrixB = reader.readLine();
                        } catch (IOException ex) {
                            System.out.println("Error:" + ex);
                        }
                        if (new File(pathMatrixB).exists()) {
                            break;
                        }
                        System.out.println("The file is not found.");
                        System.out.println("Specify the path repeatedly.");

                    }

                    System.out.println("Specify the path to the matrix in which to save result.");
                    String pathMatrixC = "";

                    while (true) {
                        try {
                            pathMatrixC = reader.readLine();
                        } catch (IOException ex) {
                            System.out.println("Error:" + ex);
                        }
                        try {
                            FileWriter writer = new FileWriter(pathMatrixC);
                            break;
                        } catch (IOException e) {
                            System.out.println("The file cannot be created.");
                            System.out.println("Specify the path repeatedly.");
                        }

                    }

                    try {
                        matrixA = ReaderMatrix.readFromFile(pathMatrixA, DataType.DOUBLE);
                        matrixB = ReaderMatrix.readFromFile(pathMatrixB, DataType.DOUBLE);

                        switch (numberOfThread) {
                            case "1":
                                matrixC = matrixA.multiply(matrixB);
                                break;
                            case "2":
                                matrixC = matrixA.multiplyThread(matrixB);
                                break;
                        }
                    } catch (IllegalSizesException |
                             IncorrectFormatOfData |
                             MatrixIndexOutOfBoundsException ex) {
                        System.out.println("Error:" + ex);
                    }

                    matrixC.write(pathMatrixC);

                    System.out.println();
                    System.out.println("To continue press \"Enter\"");
                    System.out.println("To return to the menu input \"1\"");
                    System.out.println("To stop the running program input \"0\".");

                    try {
                        exit = reader.readLine();
                    } catch (IOException ex) {
                        System.out.println("Error:" + ex);
                    }

                    if (exit.equals("1")) {
                        inputNumber = true;
                        break;
                    } else if (exit.equals("0")) {
                        inputNumber = true;
                        worker = false;
                        break;
                    } else {

                    }

                    break;
                default:
                    if (!inputNumber) {
                        System.err.println("The incorrect value is input. Please try again.");
                    }

            }
        }

        System.out.println("Author - Artur Buzov.");
    }
}
