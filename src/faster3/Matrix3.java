package faster3;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Matrix3 {

    int values[][];
    int rows;
    int columns;

    public Matrix3(int r, int c) {
        this.rows = r;
        this.columns = c;
        this.values = new int[r][c];
    }

    void randomize() {
        Random generator = new Random();

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                this.values[r][c] = generator.nextInt(10);
            }
        }
    }

    @Override
    public String toString() {
        String out = "";
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                if (c == 0) {
                    out += "[";
                } else {
                    out += "\t";
                }
                out += this.values[r][c];
            }
            out += "]\n";
        }

        return out;
    }

    @Override
    public boolean equals(Object obj) {
        Matrix3 other = (Matrix3) obj;
        if (this.columns != other.columns || this.rows != other.rows) {
            return false;
        }
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                if (this.values[r][c] != other.values[r][c]) {
                    return false;
                }
            }
        }

        return true;
    }

    // matrix multiplication using single thread
    public Matrix3 times(Matrix3 other) {
        assert (this.columns == other.rows);
        Matrix3 out = new Matrix3(this.rows, other.columns);

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < other.columns; c++) {
                int dotProduct = 0;
                for (int z = 0; z < this.columns; z++) {
                    dotProduct += this.values[r][z] * other.values[z][c];
                }
                out.values[r][c] = dotProduct;
            }
        }

        return out;
    }
    
    ExecutorService threadExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Matrix3 ptimes(Matrix3 other) throws InterruptedException, ExecutionException {
        assert (this.columns == other.rows);
        Matrix3 out = new Matrix3(this.rows, other.columns);

        Future futures[] = new Future[rows];
        for (int r = 0; r < this.rows; r++) {
            futures[r] = threadExecutor.submit(new HelperThread(r, this, other, out));
        }
        for (Future f : futures) {
            f.get();
        }

        return out;
    }

    private class HelperThread implements Callable<Object> {

        private int row;
        private Matrix3 a;
        private Matrix3 b;
        private Matrix3 out;

        HelperThread(int r, Matrix3 a, Matrix3 b, Matrix3 o) {
            this.row = r;
            this.a = a;
            this.b = b;
            this.out = o;
        }

        public String call() throws Exception {
            int dotProduct = 0;

            for (int c = 0; c < b.columns; c++) {
                for (int z = 0; z < a.columns; z++) {
                    dotProduct += this.a.values[row][z] * this.b.values[z][c];
                }
                this.out.values[row][c] = dotProduct;
            }
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size = 300;

        Matrix3 a = new Matrix3(size, size);
        a.randomize();

        Matrix3 b = new Matrix3(size, size);
        b.randomize();

        /*System.out.println("Warmup");
        for (int t = 0; t < 1000; t++) {
            Matrix3 c = a.ptimes(b);
            Matrix3 d = a.times(b);
            assert (c.equals(d));
        }*/

        System.out.println("Single Threaded");

        for (int t = 0; t < 10; t++) {
            long start = System.nanoTime();
            Matrix3 d = a.times(b);
            long finish = System.nanoTime();
            System.out.println((finish - start) / 1000000.0 + " milliseconds");
        }

        System.out.println("Multi Threaded with " + Runtime.getRuntime().availableProcessors() + " threads");

        for (int t = 0; t < 10; t++) {
            long start = System.nanoTime();
            Matrix3 c = a.ptimes(b);
            long finish = System.nanoTime();
            System.out.println((finish - start) / 1000000.0 + " milliseconds");

            Matrix3 d = a.times(b);
            assert (c.equals(d));
        }

        System.exit(0);
    }
}
