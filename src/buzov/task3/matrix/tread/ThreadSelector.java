package buzov.task3.matrix.tread;

import buzov.task3.matrix.DataType;
import buzov.task3.matrix.Matrix;

public class ThreadSelector {
    Matrix A = null;
    Matrix B = null;
    Matrix C = null;
    
    public ThreadSelector(Matrix A, Matrix B, Matrix C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }
    
    public Runnable getThread(DataType dataType) {
        Runnable thread = null;
        switch (dataType) {
            case DOUBLE:
                thread = new MultiplyThreadDouble(A, B, C);
                break;
            case ARRAY:
                thread = new MultiplyThreadArray(A, B, C);
                break;
        }

        return thread;
    }
}
