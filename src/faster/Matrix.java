package faster;

import java.util.Random;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Matrix {     
     int values[][];
     int rows;
     int columns;
     
     public Matrix(int r, int c) {
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
     
     public String toString() {
          String out = "";
          for (int r = 0; r < this.rows; r++) {
               for (int c = 0; c < this.columns; c++) {
                    if (c == 0) out += "[";
                    else out += "\t";
                    out += this.values[r][c];
               }
               out += "]\n";
          }
          
          return out;
     }
     
     public boolean equals(Object obj) {
          Matrix other = (Matrix) obj;
          if (this.columns != other.columns || this.rows != other.rows)  {
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
     public Matrix times(Matrix other) {
          assert(this.columns == other.rows);
          Matrix out = new Matrix(this.rows, other.columns);
          
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
     
     
     
     
     
     
     
     
     // matrix multiplication with many threads
     public Matrix ptimes(Matrix other, int numberOfThreads) throws InterruptedException { // parallel
          assert(this.columns == other.rows);
          Matrix out = new Matrix(this.rows, other.columns);
          
          
          ExecutorService threadExecutor = Executors.newFixedThreadPool(numberOfThreads);
          
          for (int r = 0; r < this.rows; r++) {
               for (int c = 0; c < other.columns; c++) {
                    threadExecutor.execute(new HelperThread(r, c, this, other, out));
               }
          }
          
          
          threadExecutor.shutdown();
          threadExecutor.awaitTermination(2, TimeUnit.DAYS);
          
          return out;
     }
     
     private class HelperThread implements Runnable {
          private int row;
          private int col;
          private Matrix a;
          private Matrix b;
          private Matrix out;
          
          HelperThread(int r, int c, Matrix a, Matrix b, Matrix o) {
               this.row = r;
               this.col = c;
               this.a = a;
               this.b = b;
               this.out = o;
          }
          
          public void run() {
               int dotProduct = 0;
               
               for (int z = 0; z < a.columns; z++) {
                    dotProduct += this.a.values[row][z] * this.b.values[z][col];
               }
               this.out.values[row][col] = dotProduct;
          }
     }
     
     
     
     
     public static void main(String[] args) throws InterruptedException {
          int size = 100;
          
          Matrix a = new Matrix(size, size);
          a.randomize();     
          
          Matrix b = new Matrix(size, size);
          b.randomize();
          
          
          for (int t = 1; t < 15; t++) {
               long start = new Date().getTime();
               System.out.print(t + " threads: ");
               
               Matrix c = a.ptimes(b, t);
               //System.out.println(c);
     
               long finish = new Date().getTime();
               System.out.println((finish - start) + " milliseconds");
               
               Matrix d = a.times(b);
               assert(c.equals(d));
          }
     }
}